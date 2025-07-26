package com.saif.business.transactions

import com.saif.business.BusinessErrorState
import com.saif.business.BusinessResult
import com.saif.shared.repository.TransactionRepository
import com.saif.shared.utils.Status

class GetTransactionsUseCase(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(): BusinessResult<List<PaymentSuccessResponse>> {

        val response = repository.getTransactions()
        return when (
            response.status
        ) {
            Status.SUCCESS -> {
                BusinessResult.Success(
                    data = response.data?.map { it.toPaymentSuccessResponse() } ?: emptyList()
                )
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
