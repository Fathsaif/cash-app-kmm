package com.saif.shared.repository.models

import kotlinx.serialization.Serializable

@Serializable
data class PaymentResponseDTO(
    val id: String,
    val recipientEmail: String,
    val amount: Double,
    val currency: String,
    val timestamp: String
)
