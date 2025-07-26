package com.saif.shared.repository.remoteDataSource

import com.google.firebase.firestore.FirebaseFirestore
import com.saif.shared.repository.models.PaymentRequest
import com.saif.shared.repository.models.PaymentResponseDTO
import kotlinx.coroutines.tasks.await

actual class FirestoreTransactionDataSource {
    private val collection = FirebaseFirestore.getInstance().collection("transactions")

    actual suspend fun saveTransaction(
        payment: PaymentRequest,
        backendTransactionId: String,
        timestamp: String
    ) {
        val data = mapOf(
            "id" to backendTransactionId,
            "recipientEmail" to payment.email,
            "amount" to payment.amount,
            "currency" to payment.currency,
            "timestamp" to timestamp
        )

        collection.add(data).await()
    }

    actual suspend fun getTransactions(): List<PaymentResponseDTO> {
        val snapshot = collection.get().await()
        return snapshot.documents.mapNotNull { doc ->
            val data = doc.data ?: return@mapNotNull null
            PaymentResponseDTO(
                id = data["id"] as? String ?: "",
                recipientEmail = data["recipientEmail"] as? String ?: "",
                amount = (data["amount"] as? Number)?.toDouble() ?: 0.0,
                currency = data["currency"] as? String ?: "",
                timestamp = data["timestamp"] as? String ?: ""
            )
        }
    }
}
