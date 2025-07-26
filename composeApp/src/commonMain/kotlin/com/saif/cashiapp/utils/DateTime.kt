package com.saif.cashiapp.utils

expect class DateTime() {
    fun getFormattedDate(
        iso8601Timestamp: String,
        format: String,
    ): String
}
