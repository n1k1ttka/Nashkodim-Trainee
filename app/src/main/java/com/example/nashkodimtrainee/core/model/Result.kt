package com.example.nashkodimtrainee.core.model

sealed class NetworkResult<T> {

class Pending<T>: NetworkResult<T>()

class Success<T>(val data: T) : NetworkResult<T>()

class ErrorServer<T>(val code: Int, val msg: String) : NetworkResult<T>()

class Error<T>(val e: Throwable) : NetworkResult<T>()
}

suspend fun <T> NetworkResult<T>?.onSuccess(): T? {
    return if (this is NetworkResult.Success)
        this.data
    else
        null
}

suspend fun <T> NetworkResult<T>?.onError(): Throwable? {
    return if (this is NetworkResult.Error)
        this.e
    else
        null
}