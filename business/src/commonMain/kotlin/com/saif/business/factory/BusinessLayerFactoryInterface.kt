package com.saif.business.factory

import com.saif.shared.models.ConfigurationRepoInfoInterface

interface BusinessLayerFactoryInterface {
    fun initBusinessLayer(configurationInfo: ConfigurationRepoInfoInterface)
}