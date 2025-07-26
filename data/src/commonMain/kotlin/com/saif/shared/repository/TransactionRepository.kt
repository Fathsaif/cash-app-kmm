package com.saif.shared.repository

import com.saif.shared.repository.models.PaymentRequest
import com.saif.shared.repository.models.PaymentResponseDTO
import com.saif.shared.utils.Result

interface TransactionRepository {
    suspend fun sendPayment(payment: PaymentRequest): Result<String>
    suspend fun getTransactions(): Result<List<PaymentResponseDTO>>
}