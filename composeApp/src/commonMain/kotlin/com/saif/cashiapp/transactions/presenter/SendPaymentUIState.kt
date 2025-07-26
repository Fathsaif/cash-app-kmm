package com.saif.cashiapp.transactions.presenter

sealed class SendPaymentUiState {
    object Idle : SendPaymentUiState()
    object Loading : SendPaymentUiState()
    data class Success(val message: String) : SendPaymentUiState()
    data class Error(val message: String) : SendPaymentUiState()
}
