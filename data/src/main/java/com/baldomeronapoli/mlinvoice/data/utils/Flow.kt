package com.baldomeronapoli.mlinvoice.data.utils

import com.baldomeronapoli.mlinvoice.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


inline fun <T> toSecureFlow(crossinline function: suspend () -> T): Flow<NetworkResult<T>> =
    flow {
        emit(NetworkResult.Loading(true))
        val result = function()
        emit(NetworkResult.Success(result))

    }.catch {
        emit(NetworkResult.Error(exception = it.message.toString(), data = null))

    }