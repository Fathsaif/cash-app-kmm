package com.saif.shared.kmmNetworkWrapper

import com.saif.shared.configuration.ConfigurationInfoInterface

interface ServiceWrapperFactory {
    fun createServiceWrapper(configurationInfo: ConfigurationInfoInterface): KtorServiceWrapper
}

class NetworkServiceWrapperFactory : ServiceWrapperFactory {
    override fun createServiceWrapper(
        configurationInfo: ConfigurationInfoInterface
    ): KtorServiceWrapper {
        val httpClientFactory: HttpClientBuilderFactory = KtorHttpClientBuilderFactory()
        val httpClientBuilder: HttpClientBuilder = httpClientFactory.createHttpClientBuilder()

        val httpClient =
            (httpClientBuilder as KtorHttpClientBuilder).buildHttpClient(configurationInfo)

        return KtorServiceWrapper(httpClient)
    }
}
