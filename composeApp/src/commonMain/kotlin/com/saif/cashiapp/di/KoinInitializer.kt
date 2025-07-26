package com.saif.cashiapp.di

import com.saif.business.factory.BusinessLayerFactory
import com.saif.shared.models.ConfigurationRepoInfo
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class KoinInitializer {
    fun init(appDeclaration: KoinAppDeclaration = {}) {
        startKoin {
            appDeclaration()
            modules(transactionsModule, historyModule)
        }
        val configuration = ConfigurationRepoInfo(
            "https://transaction-api-hcyc.onrender.com/",
            30000, false
        )
        BusinessLayerFactory.getInstance()
            .initBusinessLayer(configuration)
    }
}