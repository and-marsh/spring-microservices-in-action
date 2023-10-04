package com.optimagrowth.organization.utils

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class UserContextFilter : Filter {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse?, filterChain: FilterChain) {
        val httpServletRequest = servletRequest as HttpServletRequest
        httpServletRequest.getHeader(UserContext.CORRELATION_ID)?.let { header ->
            UserContextHolder.context?.correlationId = header
        }
        httpServletRequest.getHeader(UserContext.USER_ID)?.let { header ->
            UserContextHolder.context?.userId = header
        }
        httpServletRequest.getHeader(UserContext.AUTH_TOKEN)?.let { header ->
            UserContextHolder.context?.authToken = header
        }
        httpServletRequest.getHeader(UserContext.ORGANIZATION_ID)?.let { header ->
            UserContextHolder.context?.organizationId = header
        }

        println("Using thread: ${Thread.currentThread().id}")
        logger.debug("UserContextFilter Correlation id: {}", UserContextHolder.context?.correlationId)

        filterChain.doFilter(httpServletRequest, servletResponse)
    }

    companion object {

        private val logger = LoggerFactory.getLogger(UserContextFilter::class.java)
    }
}
