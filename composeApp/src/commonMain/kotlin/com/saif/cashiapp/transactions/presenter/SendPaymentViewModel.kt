package com.saif.cashiapp.transactions.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saif.business.BusinessResult
import com.saif.business.transactions.SendPaymentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SendPaymentViewModel(
    private val sendPaymentUseCase: SendPaymentUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<SendPaymentUiState>(SendPaymentUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun sendPayment(
        email: String,
        amountText: String,
        currency: String
    ) {

        viewModelScope.launch {
            _uiState.value = SendPaymentUiState.Loading

            val result = sendPaymentUseCase(email, amountText, currency)

            _uiState.value = when (result) {
                is BusinessResult.Success -> SendPaymentUiState.Success("Payment sent successfully!")
                is BusinessResult.Error -> SendPaymentUiState.Error(
                    result.errorMessage ?: "Something went wrong"
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = SendPaymentUiState.Idle
    }

}