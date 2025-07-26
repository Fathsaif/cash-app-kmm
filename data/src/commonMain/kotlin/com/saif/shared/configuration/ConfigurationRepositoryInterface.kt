package com.saif.shared.configuration

interface ConfigurationRepositoryInterface {
    fun configureHttpClient(
        configurationInfo: ConfigurationInfoInterface,
    )
}