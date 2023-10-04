package com.optimagrowth.gatewayserver.filters

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange

@Component
object FilterUtils {

    fun HttpHeaders.getCorrelationId(): String? =
        this[CORRELATION_ID]?.stream()?.findFirst()?.get()

    private fun ServerWebExchange.setRequestHeader(name: String, value: String?): ServerWebExchange {
        val requestWithHeader = request
            .mutate()
            .header(name, value)
            .build()

        return mutate()
            .request(requestWithHeader)
            .build()
    }

    fun ServerWebExchange.setCorrelationId(correlationId: String?): ServerWebExchange =
        setRequestHeader(CORRELATION_ID, correlationId)

    const val CORRELATION_ID = "tmx-correlation-id"
    const val AUTH_TOKEN = "tmx-auth-token"
    const val USER_ID = "tmx-user-id"
    const val ORG_ID = "tmx-org-id"
    const val PRE_FILTER_TYPE = "pre"
    const val POST_FILTER_TYPE = "post"
    const val ROUTE_FILTER_TYPE = "route"
}
