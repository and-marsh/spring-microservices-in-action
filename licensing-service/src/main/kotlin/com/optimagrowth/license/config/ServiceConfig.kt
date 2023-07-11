package com.optimagrowth.license.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "example")
data class ServiceConfig constructor(
    var property: String? = null
)
