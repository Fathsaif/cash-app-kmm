package com.saif.shared.kmmNetworkWrapper

import com.saif.shared.utils.NetworkResponse
import com.saif.shared.utils.handleThrowable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import io.ktor.http.parameters

class KtorServiceWrapper(
    val httpClient: HttpClient
) {
    suspend inline fun <reified T, reified E : Any> getRequest(url: String): NetworkResponse<T, E> {
        return try {
            val result = httpClient.get {
                url(url)
                method = HttpMethod.Get
            }.body<T>()
            NetworkResponse.Success(result)
        } catch (ex: Throwable) {
            handleThrowable(ex)
        }
    }

    suspend inline fun <reified T, reified E : Any> getRequestWithQueries(
        url: String, queries: Map<String, Any>
    ): NetworkResponse<T, E> {
        return try {
            val result = httpClient.get {
                url(url)
                queries.forEach {
                    parameter(it.key, it.value)
                }
                method = HttpMethod.Get
            }.body<T>()

            NetworkResponse.Success(result)
        } catch (ex: Throwable) {
            handleThrowable(ex)
        }
    }

    suspend inline fun <reified T, reified E : Any> getRequestWithUrlParameters(
        url: String, parameters: List<Any>
    ): NetworkResponse<T, E> {
        return try {
            val result = httpClient.get {
                url(url) {
                    pathSegments = parameters.map { it.toString() }
                }
            }.body<T>()
            NetworkResponse.Success(result)
        } catch (ex: Throwable) {
            handleThrowable(ex)
        }
    }

    suspend inline fun <reified T, reified E : Any> sendRequestWithFields(
        url: String, parameters: Map<String, Any>
    ): NetworkResponse<T, E> {
        return try {
            val result =
                httpClient.submitForm(url = url, formParameters = parameters {
                    parameters.forEach { map ->
                        append(map.key, map.value.toString())
                    }
                }).body<T>()
            NetworkResponse.Success(result)
        } catch (ex: Throwable) {
            return handleThrowable(ex)
        }
    }

    suspend inline fun <reified T, reified E : Any> sendRequestWithBody(
        url: String,
        body: Any
    ): NetworkResponse<T, E> {
        return try {
            val result =
                httpClient.post {
                    url(url)
                    setBody(body)
                }.body<T>()
            NetworkResponse.Success(result)
        } catch (ex: Throwable) {
            println("Error in KtorServiceWrapper: ${ex.message}")
            println(ex.message)
            handleThrowable(ex)
        }

    }
}
