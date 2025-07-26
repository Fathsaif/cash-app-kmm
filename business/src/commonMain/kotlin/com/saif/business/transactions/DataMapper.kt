package com.saif.business.transactions

import com.saif.business.BusinessErrorState
import com.saif.shared.utils.ErrorState

fun ErrorState.toBusinessError(): BusinessErrorState {
    return when (this) {
        ErrorState.SERVER_ERROR -> BusinessErrorState.SERVER_ERROR
        ErrorState.NETWORK_ERROR -> BusinessErrorState.NETWORK_ERROR
        ErrorState.UNKNOWN_ERROR -> BusinessErrorState.UNKNOWN_ERROR
        ErrorState.NO_DATA_ERROR -> BusinessErrorState.NO_DATA_ERROR
        ErrorState.VALIDATION_ERROR -> BusinessErrorState.VALIDATION_ERROR
    }
}