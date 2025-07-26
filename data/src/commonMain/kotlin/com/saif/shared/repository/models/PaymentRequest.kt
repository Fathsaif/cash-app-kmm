package com.saif.shared.repository.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class PaymentRequest(
    val email: String,
    val amount: Double,
    val currency: String
){
    fun jsonEncode(): String = Json.encodeToString(this)
}
