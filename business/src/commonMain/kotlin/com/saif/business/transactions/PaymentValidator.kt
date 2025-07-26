package com.saif.business.transactions

object PaymentValidator {
    fun isValidEmail(email: String): Boolean =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").matches(email)

    fun isValidAmount(amount: String?): Boolean {
        if (amount.isNullOrEmpty()) return false
        val regex = Regex("^[0-9]+(\\.[0-9]{1,2})?\$")
        return regex.matches(amount) && amount.toDoubleOrNull() != null
    }

    fun isSupportedCurrency(currency: String): Boolean =
        currency in listOf("USD", "EUR")
}