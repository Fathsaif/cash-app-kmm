package com.saif.shared

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp

actual object AppContext {
    private var applicationContext: Context? = null

    val context
        get() = applicationContext
            ?: error("Android context has not been set. Please call setContext in your Application's onCreate.")

    fun setContext(context: Context) {
        applicationContext = context.applicationContext
    }
}

val okhttpEngine = OkHttp.create {
}

actual fun httpClientBuilder(config: HttpClientConfig<*>.() -> Unit) = HttpClient(okhttpEngine) {
    config(this)
}