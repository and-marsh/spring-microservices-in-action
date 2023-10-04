package com.optimagrowth.gatewayserver.filters

import com.optimagrowth.gatewayserver.filters.FilterUtils.getCorrelationId
import com.optimagrowth.gatewayserver.filters.FilterUtils.setCorrelationId
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.UUID

@Order(1)
@Component
class TrackingFilter : GlobalFilter {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val requestHeaders = exchange.request.headers
        val modifiedExchange = if (requestHeaders.isCorrelationIdPresent) {
            logger.debug("tmx-correlation-id found in tracking filter: {}.", requestHeaders.getCorrelationId())
            exchange
        } else {
            val correlationID = generateCorrelationId()
            logger.debug("tmx-correlation-id generated in tracking filter: {}.", correlationID)
            exchange.setCorrelationId(correlationID)
        }
        return chain.filter(modifiedExchange)
    }

    private val HttpHeaders.isCorrelationIdPresent: Boolean
        get() = getCorrelationId() != null

    private fun generateCorrelationId(): String =
        UUID.randomUUID().toString()

    companion object {

        private val logger = LoggerFactory.getLogger(TrackingFilter::class.java)
    }
}
