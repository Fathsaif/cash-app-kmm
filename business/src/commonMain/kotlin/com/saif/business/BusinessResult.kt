package com.saif.business

sealed class BusinessResult<T>(
    val status: Status,
    val data: T? = null,
    val errorMessage: String? = null,
    val errorCode: BusinessErrorState? = null,
    val exception: Throwable? = null
) {

    class Success<T>(data: T, errorMessage: String? = null) :
        BusinessResult<T>(Status.SUCCESS, data, errorMessage)

    class Error<T>(
        data: T? = null,
        errorMessage: String? = null,
        errorCode: BusinessErrorState?,
        exception: Throwable? = null
    ) :
        BusinessResult<T>(Status.ERROR, data, errorMessage, errorCode, exception)
}

enum class Status {
    SUCCESS,
    ERROR
}
