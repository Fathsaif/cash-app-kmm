package com.saif.shared.configuration
interface ConfigurationInfoInterface {
    val baseUrl: String
    val timeOut: Long
    val isDebug: Boolean
}

data class ConfigurationInfo(
    override val baseUrl: String,
    override val timeOut: Long, override val isDebug: Boolean
) : ConfigurationInfoInterface