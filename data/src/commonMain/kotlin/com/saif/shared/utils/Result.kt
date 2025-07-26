package com.saif.shared.utils

sealed class Result<T>(
    val status: Status,
    val data: T? = null,
    val errorMessage: String? = null,
    val errorCode: ErrorState? = null,
    val exception: Throwable? = null
) {

    class Success<T>(data: T, errorMessage: String? = null) :
        Result<T>(Status.SUCCESS, data, errorMessage)

    class Error<T>(
        data: T? = null,
        errorMessage: String? = null,
        errorCode: ErrorState?,
        exception: Throwable? = null
    ) :
        Result<T>(Status.ERROR, data, errorMessage, errorCode, exception)
}

enum class Status {
    SUCCESS,
    ERROR
}
