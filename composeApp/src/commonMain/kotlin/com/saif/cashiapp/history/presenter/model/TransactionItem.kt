package com.saif.cashiapp.history.presenter.model

import com.saif.business.transactions.PaymentSuccessResponse

data class TransactionItem(
    val receiverEmail: String = "",
    val amount: Double,
    val currency: String,
    val date: String,
)

fun PaymentSuccessResponse.toTransactionItem(): TransactionItem {
    return TransactionItem(
        receiverEmail = this.recipientEmail,
        amount = this.amount,
        currency = this.currency,
        date = ""
    )
}
