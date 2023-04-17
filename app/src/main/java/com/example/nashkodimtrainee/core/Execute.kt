package com.example.nashkodimtrainee.core

import com.example.nashkodimtrainee.core.model.NetworkResult
import retrofit2.HttpException
import retrofit2.Response


suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkResult<T>? {
    return try {
        val response = execute()
        val body = response.body()
        when (response.code()) {
            200 -> {
                if (body != null) NetworkResult.Success<T>(data = body)
                else null
            }
            else -> {
                NetworkResult.ErrorServer<T>(code = 0, response.errorBody().toString())
            }
        }
    } catch (e: HttpException) {
        NetworkResult.Error<T>(e)
    } catch (e: Throwable) {
        NetworkResult.Error<T>(e)
    }
}