package com.saif.business.transactions

import com.saif.shared.repository.models.PaymentResponseDTO

data class PaymentSuccessResponse(
    val recipientEmail: String = "",
    val transactionId: String,
    val amount: Double,
    val currency: String,
    val timestamp: String
)

fun PaymentResponseDTO.toPaymentSuccessResponse(): PaymentSuccessResponse {
    return PaymentSuccessResponse(
        recipientEmail = this.recipientEmail,
        transactionId = this.id,
        amount = amount,
        currency = currency,
        timestamp = timestamp
    )
}
