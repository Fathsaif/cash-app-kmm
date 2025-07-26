package com.saif.shared.repository.remoteDataSource

import com.saif.shared.repository.models.PaymentRequest
import com.saif.shared.repository.models.PaymentResponseDTO
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

expect class FirestoreTransactionDataSource {
    suspend fun saveTransaction(
        payment: PaymentRequest,
        backendTransactionId: String,
        timestamp: String = ""
    )

    suspend fun getTransactions(): List<PaymentResponseDTO>
}
