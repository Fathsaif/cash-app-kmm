package com.saif.shared
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin

actual object AppContext
actual fun httpClientBuilder(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
    config(this)
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}