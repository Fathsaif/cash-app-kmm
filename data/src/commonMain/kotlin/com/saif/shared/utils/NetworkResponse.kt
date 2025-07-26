package com.saif.shared.utils
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse

sealed class NetworkResponse<out T, out E> {

    data class Success<T>(val body: T) : NetworkResponse<T, Nothing>()

    sealed class Error<E> : NetworkResponse<Nothing, E>() {

        data class ServerError<E>(
            val code: Int,
            val errorBody: E?,
            val errorMessage: String?,
        ) : Error<E>()

        data class NetworkError(
            val message: String?,
            val errorMessage: String?,
        ) : Error<Nothing>()

        data class UnknownError(
            val message: String?,
            val errorMessage: String?,
        ) : Error<Nothing>()
    }
}

class HttpExceptions(
    response: HttpResponse,
    failureReason: String?,
    cachedResponseText: String,
) : ResponseException(response, cachedResponseText) {
    override val message: String = "Status: ${response.status}" + " Failure: $failureReason"
}