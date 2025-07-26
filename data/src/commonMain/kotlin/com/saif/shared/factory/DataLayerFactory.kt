package com.saif.shared.factory

import com.saif.shared.configuration.ConfigurationInfo
import com.saif.shared.configuration.ConfigurationInfoInterface
import com.saif.shared.di.networkModule
import com.saif.shared.di.platformModule
import com.saif.shared.di.transactionsRepoModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules

class DataLayerFactory() : DataLayerFactoryInterface, KoinComponent {

    private fun initKoin() = loadKoinModules(
        listOf(networkModule, platformModule, transactionsRepoModule)
    )

    init {
        initKoin()
    }

    companion object Companion {
        private lateinit var instance: DataLayerFactoryInterface
        fun getInstance(): DataLayerFactoryInterface {
            if (!this::instance.isInitialized) {
                instance = DataLayerFactory()
            }
            return instance
        }
    }

    override fun initNetworkLayer(
        configurationInfo: ConfigurationInfoInterface
    ) {
        getKoin().declare<ConfigurationInfoInterface>(
            ConfigurationInfo(
                configurationInfo.baseUrl,
                configurationInfo.timeOut, configurationInfo.isDebug
            )
        )
    }
}