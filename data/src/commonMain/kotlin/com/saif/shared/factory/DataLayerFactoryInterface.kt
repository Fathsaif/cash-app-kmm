package com.saif.shared.factory

import com.saif.shared.configuration.ConfigurationInfoInterface

interface DataLayerFactoryInterface {
    fun initNetworkLayer(
        configurationInfo: ConfigurationInfoInterface
    )
}
