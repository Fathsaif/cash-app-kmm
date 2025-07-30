package com.saif.shared

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

expect fun httpClientBuilder(config: HttpClientConfig<*>.() -> Unit): HttpClient
