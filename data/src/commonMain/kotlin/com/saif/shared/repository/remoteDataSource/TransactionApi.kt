package com.saif.shared.repository.remoteDataSource

import com.saif.shared.kmmNetworkWrapper.KtorServiceWrapper
import com.saif.shared.repository.models.PaymentResponseDTO
import com.saif.shared.utils.NetworkResponse

class TransactionApi(
    private val service: KtorServiceWrapper
) {
    suspend fun sendPayment(payment: String): NetworkResponse<String, String> {
        return service.sendRequestWithBody("transaction", payment)
    }

    suspend fun getTransactions(): NetworkResponse<List<PaymentResponseDTO>, String> {
        return service.getRequest("transaction")
    }
}
