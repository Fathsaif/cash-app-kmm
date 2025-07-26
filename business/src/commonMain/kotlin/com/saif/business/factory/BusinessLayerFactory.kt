package com.saif.business.factory

import com.saif.business.di.paymentsBusinessModule
import com.saif.shared.configuration.ConfigurationInfo
import com.saif.shared.factory.DataLayerFactory
import com.saif.shared.models.ConfigurationRepoInfoInterface
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules

class BusinessLayerFactory private constructor() : BusinessLayerFactoryInterface, KoinComponent {

    private fun initKoin() = loadKoinModules(
        listOf(
            paymentsBusinessModule
        )
    )

    init {
        initKoin()
    }

    companion object {
        private lateinit var instance: BusinessLayerFactoryInterface
        fun getInstance(): BusinessLayerFactoryInterface {
            if (!this::instance.isInitialized) {
                instance = BusinessLayerFactory()
            }
            return instance
        }
    }

    override fun initBusinessLayer(
        configurationInfo: ConfigurationRepoInfoInterface,
    ) {
        DataLayerFactory.getInstance().initNetworkLayer(
            ConfigurationInfo(
                configurationInfo.baseUrl,
                configurationInfo.timeOut,
                configurationInfo.isDebug
            )
        )
    }
}
