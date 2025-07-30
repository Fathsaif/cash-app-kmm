package com.saif.shared

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp

val okhttpEngine = OkHttp.create {
}

actual fun httpClientBuilder(config: HttpClientConfig<*>.() -> Unit) = HttpClient(okhttpEngine) {
    config(this)
}
