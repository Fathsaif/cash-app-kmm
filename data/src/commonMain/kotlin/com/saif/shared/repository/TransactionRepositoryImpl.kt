package com.saif.shared.repository

import com.saif.shared.repository.models.PaymentRequest
import com.saif.shared.repository.models.PaymentResponseDTO
import com.saif.shared.repository.remoteDataSource.FirestoreTransactionDataSource
import com.saif.shared.repository.remoteDataSource.TransactionApi
import com.saif.shared.utils.ErrorState
import com.saif.shared.utils.NetworkResponse
import com.saif.shared.utils.Result

class TransactionRepositoryImpl(
    private val transactionsApi: TransactionApi,
    private val firestore: FirestoreTransactionDataSource
) : TransactionRepository {
    override suspend fun sendPayment(payment: PaymentRequest): Result<String> {
        return when (val response = transactionsApi.sendPayment(payment.jsonEncode())) {
            is NetworkResponse.Error.NetworkError -> {
                Result.Error(errorCode = ErrorState.NETWORK_ERROR)
            }

            is NetworkResponse.Error.ServerError -> {
                Result.Error(errorCode = ErrorState.SERVER_ERROR)
            }

            is NetworkResponse.Error.UnknownError -> {
                Result.Error(errorCode = ErrorState.UNKNOWN_ERROR)
            }

            is NetworkResponse.Success -> {
                firestore.saveTransaction(payment, response.body)
                Result.Success(data = response.body)
            }
        }
    }

    override suspend fun getTransactions(): Result<List<PaymentResponseDTO>> {
        return try {
            val list = firestore.getTransactions()
            Result.Success(data = list)
        } catch (e: Exception) {
            Result.Error(errorCode = ErrorState.UNKNOWN_ERROR, exception = e)
        }
    }
}
