package com.saif.cashiapp.history.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saif.business.BusinessResult
import com.saif.business.transactions.GetTransactionsUseCase
import com.saif.cashiapp.history.presenter.model.toTransactionItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsHistoryViewmodel(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<TransactionHistoryUiState>(TransactionHistoryUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getTransactionsHistory()
    }

    private fun getTransactionsHistory() {
        _uiState.value = TransactionHistoryUiState.Loading
        viewModelScope.launch {
            val result = getTransactionsUseCase()

            _uiState.value = when (result) {
                is BusinessResult.Success -> TransactionHistoryUiState.Success(result.data?.map { it.toTransactionItem() }
                    ?: emptyList())

                is BusinessResult.Error -> TransactionHistoryUiState.Error(
                    result.errorMessage ?: "Something went wrong"
                )
            }
        }
    }
}