package com.saif.shared.kmmNetworkWrapper

internal interface HttpClientBuilderFactory {
    fun createHttpClientBuilder(): KtorHttpClientBuilder
}

internal class KtorHttpClientBuilderFactory : HttpClientBuilderFactory {
    override fun createHttpClientBuilder(): KtorHttpClientBuilder {
        return KtorHttpClientBuilder()
    }
}
