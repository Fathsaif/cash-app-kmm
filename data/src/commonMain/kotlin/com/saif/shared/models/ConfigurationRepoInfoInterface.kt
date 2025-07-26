package com.saif.shared.models

import com.saif.shared.configuration.ConfigurationInfo
import com.saif.shared.configuration.ConfigurationInfoInterface

interface ConfigurationRepoInfoInterface {
    val baseUrl: String
    val timeOut: Long
    val isDebug: Boolean
}

data class ConfigurationRepoInfo(
    override val baseUrl: String,
    override val timeOut: Long, override val isDebug: Boolean
) : ConfigurationRepoInfoInterface

fun ConfigurationRepoInfoInterface.convertToConfigurationInfo(): ConfigurationInfoInterface {
    return ConfigurationInfo(
        baseUrl = baseUrl,
        timeOut = timeOut,
        isDebug = isDebug
    )
}
