package com.baldomeronapoli.mlinvoice.presenter.state

sealed class BaseUiState<out T>(open val data: T?, open val message: String?) {

    data class Loaded<out T>(override val data: T, override val message: String? = null) :
        BaseUiState<T>(data = data, message = null)

    data class NotStarted<out T>(override val data: T? = null) :
        BaseUiState<T>(data = data, message = null)

    data class Error<out T>(override val message: String, override val data: T? = null) :
        BaseUiState<T>(data = data, message = message)

    data class Loading<out T>(val isLoading: Boolean = true) :
        BaseUiState<T>(data = null, message = null)
}