package com.saif.shared.repository.remoteDataSource

import com.saif.shared.repository.models.PaymentRequest
import com.saif.shared.repository.models.PaymentResponseDTO

actual class FirestoreTransactionDataSource {
    actual suspend fun saveTransaction(
        payment: PaymentRequest,
        backendTransactionId: String,
        timestamp: String
    ) {
    }

    actual suspend fun getTransactions(): List<PaymentResponseDTO> {
        return listOf(
            PaymentResponseDTO(
                id = "1",
                recipientEmail = "email1@gmail.com",
                amount = 100.0,
                currency = "USD",
                timestamp = "2023-10-01T12:00:00Z"
            ),
            PaymentResponseDTO(
                id = "2",
                recipientEmail = "email2@gmail.com",
                amount = 200.0,
                currency = "USD",
                timestamp = "2023-10-02T12:00:00Z"
            ),
            PaymentResponseDTO(
                id = "3",
                recipientEmail = "email3@gmail.com",
                amount = 300.0,
                currency = "EUR",
                timestamp = "2024-10-02T12:00:00Z"
            ),
            PaymentResponseDTO(
                id = "2",
                recipientEmail = "email2@gmail.com",
                amount = 200.0,
                currency = "USD",
                timestamp = "2023-10-02T12:00:00Z"
            ),
        )
    }
}