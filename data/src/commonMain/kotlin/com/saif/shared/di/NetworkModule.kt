package com.saif.shared.di

import com.saif.shared.kmmNetworkWrapper.HttpClientBuilderFactory
import com.saif.shared.kmmNetworkWrapper.KtorHttpClientBuilderFactory
import com.saif.shared.kmmNetworkWrapper.NetworkServiceWrapperFactory
import com.saif.shared.kmmNetworkWrapper.ServiceWrapperFactory
import com.saif.shared.configuration.ConfigurationRepository
import com.saif.shared.configuration.ConfigurationRepositoryInterface
import org.koin.dsl.module

val networkModule = module {
    factory<ConfigurationRepositoryInterface> {
        ConfigurationRepository(get())
    }

    single {
        NetworkServiceWrapperFactory().createServiceWrapper(get())
    }
    single<ServiceWrapperFactory> {
        NetworkServiceWrapperFactory()
    }

    single<HttpClientBuilderFactory> {
        KtorHttpClientBuilderFactory()
    }

}