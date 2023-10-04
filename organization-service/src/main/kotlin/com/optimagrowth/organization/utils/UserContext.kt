package com.optimagrowth.organization.utils

import org.springframework.stereotype.Component

@Component
data class UserContext(
    var correlationId: String = DEFAULT,
    var authToken: String = DEFAULT,
    var userId: String = DEFAULT,
    var organizationId: String = DEFAULT,
) {

    companion object {

        const val CORRELATION_ID = "tmx-correlation-id"
        const val AUTH_TOKEN = "tmx-auth-token"
        const val USER_ID = "tmx-user-id"
        const val ORGANIZATION_ID = "tmx-organization-id"

        const val DEFAULT = "DEFAULT"
    }
}
