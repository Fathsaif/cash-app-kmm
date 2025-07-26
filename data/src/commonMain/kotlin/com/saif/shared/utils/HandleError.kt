package com.saif.shared.utils

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

const val TAG: String = "CashiAppKMM"

suspend inline fun <reified E : Any> handleThrowable(ex: Throwable): NetworkResponse<Nothing, E> {
    return when (ex) {
        is HttpRequestTimeoutException, is SocketTimeoutException, is IOException -> {
            logRequest("${ex.message}\n ${ex.stackTraceToString()}")
            NetworkResponse.Error.NetworkError(ex.message, NETWORK_ERROR)
        }

        is RedirectResponseException -> {
            // 3xx - responses
            logRequest("${ex.response.status.description}\n ${ex.stackTraceToString()}")
            NetworkResponse.Error.ServerError(
                ex.response.status.value,
                ex.response.body(),
                SERVER_ERROR
            )
        }

        is ClientRequestException -> {
            // 4xx - responses
            logRequest("${ex.response.status.description}\n ${ex.stackTraceToString()}")
            NetworkResponse.Error.ServerError(
                ex.response.status.value,
                ex.response.body(),
                SERVER_ERROR
            )
        }

        is ServerResponseException -> {
            // 5xx - response
            logRequest("${ex.response.status.description}\n ${ex.stackTraceToString()}")
            NetworkResponse.Error.ServerError(
                ex.response.status.value,
                ex.response.body(),
                SERVER_ERROR
            )
        }

        is NoTransformationFoundException, is SerializationException -> {
            logRequest("${ex.message}\n ${ex.stackTraceToString()}")
            NetworkResponse.Error.UnknownError(ex.message, UNKNOWN_ERROR)
        }

        else -> {
            logRequest("${ex.message}\n ${ex.stackTraceToString()}")
            NetworkResponse.Error.UnknownError(ex.message, UNKNOWN_ERROR)
        }
    }
}

fun logRequest(message: String) {
    if (IS_DEBUG) {
        Logger.SIMPLE.log("$TAG: $message")
    }
}
