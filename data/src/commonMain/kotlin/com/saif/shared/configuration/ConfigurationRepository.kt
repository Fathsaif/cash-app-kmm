package com.saif.shared.configuration

import com.saif.shared.kmmNetworkWrapper.ServiceWrapperFactory

class ConfigurationRepository(private val ktorServiceFactory: ServiceWrapperFactory) :
    ConfigurationRepositoryInterface {
    override fun configureHttpClient(
        configurationInfo: ConfigurationInfoInterface,
    ) {
        ktorServiceFactory.createServiceWrapper(configurationInfo)
    }
}