package com.baldomeronapoli.mlinvoice.domain.utils

sealed class NetworkResult<out T>(val status: ApiStatus, open val data: T?, val message: String?) {

    data class Success<out T>(override val data: T) :
        NetworkResult<T>(status = ApiStatus.SUCCESS, data = data, message = null)

    data class Error<out T>(val exception: String, override val data: T? = null) :
        NetworkResult<T>(status = ApiStatus.ERROR, data = data, message = exception)

    data class Loading<out T>(val isLoading: Boolean) :
        NetworkResult<T>(status = ApiStatus.LOADING, data = null, message = null)
}

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}
