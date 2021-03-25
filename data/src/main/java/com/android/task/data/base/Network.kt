package com.android.task.data.base

import retrofit2.Response
import java.net.HttpURLConnection

class FailedRequest(val responseCode: Int, cause: Throwable? = null) : RuntimeException("Failed network call", cause)

abstract class BaseException(message: String, val errorCode: Int, cause: Throwable? = null) : RuntimeException(message) {
    init {
        cause?.let { initCause(it) }
    }
}

class UnExpectedException(cause: Throwable? = null) : BaseException(message = "UnExpected Error", errorCode = -1, cause = cause)


/** This function will extract the response value or throw an exception */
suspend fun <ResponseType> returnOrThrow(requestCall: suspend () -> Response<ResponseType>): ResponseType {
    val response = requestCall()
    return when (response.code()) {
        HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_CREATED -> response.body()!!
        else -> throw FailedRequest(response.code())
    }
}


