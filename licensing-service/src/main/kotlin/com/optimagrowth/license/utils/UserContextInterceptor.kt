package com.optimagrowth.license.utils

import com.optimagrowth.license.utils.UserContextHolder.context
import feign.RequestInterceptor
import feign.RequestTemplate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class UserContextInterceptor : RequestInterceptor {

    override fun apply(template: RequestTemplate?) {
        template?.header(UserContext.CORRELATION_ID, context?.correlationId)
        template?.header(UserContext.AUTH_TOKEN, context?.authToken)

        logger.debug("UserContextInterceptor Correlation id: {}", context?.correlationId)
    }

    companion object {

        private val logger = LoggerFactory.getLogger(UserContextInterceptor::class.java)
    }
}
