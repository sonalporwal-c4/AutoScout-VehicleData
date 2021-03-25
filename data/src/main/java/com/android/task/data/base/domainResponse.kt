package com.android.task.data.base

import com.android.task.domain.base.DomainResponse
import retrofit2.HttpException

@Suppress("TooGenericExceptionCaught")
suspend fun <T : Any> domainResponse(
    result: suspend () -> T
) =
    try {
        DomainResponse.Content(result())
    } catch (e: HttpException) {
        DomainResponse.Error(e.code(), e.localizedMessage ?: "Unknown Error")
    } catch (e: Exception) {
        // TODO Change with something else...
        DomainResponse.Error(999, "${e.javaClass.simpleName} ${e.localizedMessage}")
    }