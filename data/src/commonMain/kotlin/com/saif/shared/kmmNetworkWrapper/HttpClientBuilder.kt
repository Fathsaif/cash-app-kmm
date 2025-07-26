package com.saif.shared.kmmNetworkWrapper

import com.saif.shared.configuration.ConfigurationInfoInterface
import com.saif.shared.httpClientBuilder
import com.saif.shared.utils.BAD_REQUEST
import com.saif.shared.utils.FORBIDDEN
import com.saif.shared.utils.HttpExceptions
import com.saif.shared.utils.IS_DEBUG
import com.saif.shared.utils.NETWORK_ERROR
import com.saif.shared.utils.NOT_FOUND
import com.saif.shared.utils.SERVER_ERROR
import com.saif.shared.utils.TIMEOUT
import com.saif.shared.utils.UNAUTHORIZED
import com.saif.shared.utils.logRequest

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal interface HttpClientBuilder {
    fun buildHttpClient(configurationInfo: ConfigurationInfoInterface): HttpClient
}

internal class KtorHttpClientBuilder() :
    HttpClientBuilder {

    private lateinit var httpClient: HttpClient

    override fun buildHttpClient(configurationInfo: ConfigurationInfoInterface): HttpClient {
        setDebugMode(configurationInfo.isDebug)
        httpClient = httpClientBuilder {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }


            defaultRequest {

                url {

                    url(configurationInfo.baseUrl)

                    header(HttpHeaders.ContentType, "application/json")
                }
            }
            install(HttpTimeout) {
                requestTimeoutMillis = configurationInfo.timeOut
                connectTimeoutMillis = configurationInfo.timeOut
                socketTimeoutMillis = configurationInfo.timeOut
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        logRequest(message)
                    }
                }
                level = LogLevel.ALL
            }


            HttpResponseValidator {
                validateResponse { response ->
                    if (!response.status.isSuccess()) {
                        val failureReason = when (response.status) {
                            HttpStatusCode.BadRequest -> BAD_REQUEST
                            HttpStatusCode.Unauthorized -> UNAUTHORIZED
                            HttpStatusCode.Forbidden -> "${response.status.value} $FORBIDDEN"
                            HttpStatusCode.NotFound -> NOT_FOUND
                            HttpStatusCode.RequestTimeout -> TIMEOUT
                            in HttpStatusCode.InternalServerError..HttpStatusCode.GatewayTimeout -> "${response.status.value} $SERVER_ERROR"

                            else -> NETWORK_ERROR
                        }

                        throw HttpExceptions(
                            response = response,
                            failureReason = failureReason,
                            cachedResponseText = response.bodyAsText(),
                        )
                    }
                }
            }
        }
        return httpClient
    }

    private fun setDebugMode(debug: Boolean) {
        IS_DEBUG = debug
    }
}