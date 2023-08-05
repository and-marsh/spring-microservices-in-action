package com.optimagrowth.license

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@RefreshScope
class LicensingServiceApplication

fun main(args: Array<String>) {
    runApplication<LicensingServiceApplication>(*args)
}
