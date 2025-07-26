package com.saif.business.transactions

import com.saif.business.BusinessErrorState
import com.saif.business.BusinessResult
import com.saif.shared.repository.TransactionRepository
import com.saif.shared.repository.models.PaymentRequest
import com.saif.shared.utils.Status

class SendPaymentUseCase(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(
        email: String,
        amount: String,
        currency: String
    ): BusinessResult<String> {
        // Validate inputs
        if (!PaymentValidator.isValidEmail(email)) {
            return BusinessResult.Error(
                errorMessage = "Invalid email",
                errorCode = BusinessErrorState.VALIDATION_ERROR
            )
        }

        if (!PaymentValidator.isValidAmount(amount)) {
            return BusinessResult.Error(
                errorMessage = "Amount must be > 0",
                errorCode = BusinessErrorState.VALIDATION_ERROR
            )
        }

        if (!PaymentValidator.isSupportedCurrency(currency)) {
            return BusinessResult.Error(
                errorMessage = "Unsupported currency",
                errorCode = BusinessErrorState.VALIDATION_ERROR
            )
        }
        val response = repository.sendPayment(
            PaymentRequest(email, amount.toDoubleOrNull() ?: 0.0, currency)
        )
        return when (
            response.status
        ) {
            Status.SUCCESS -> {
                val data = response.data
                if (data != null) {
                    BusinessResult.Success(
                        data = data,
                        errorMessage = response.errorMessage
                    )
                } else {
                    BusinessResult.Error(
                        errorMessage = "No data received",
                        errorCode = BusinessErrorState.NO_DATA_ERROR
                    )
                }
            }

            Status.ERROR -> {
                BusinessResult.Error(
                    errorMessage = response.errorMessage ?: "Unknown error",
                    errorCode = response.errorCode?.toBusinessError()
                        ?: BusinessErrorState.UNKNOWN_ERROR,
                    exception = response.exception
                )
            }
        }

    }
}
