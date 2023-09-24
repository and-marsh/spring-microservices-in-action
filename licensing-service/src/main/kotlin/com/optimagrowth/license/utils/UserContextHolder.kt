package com.optimagrowth.license.utils

import org.springframework.util.Assert

object UserContextHolder {

    private val userContext = ThreadLocal<UserContext?>()
    var context: UserContext?
        get() {
            var context = userContext.get()
            if (context == null) {
                context = createEmptyContext()
                userContext.set(context)
            }
            return userContext.get()
        }
        set(context) {
            Assert.notNull(context, "Only non-null UserContext instances are permitted")
            userContext.set(context)
        }

    private fun createEmptyContext(): UserContext =
        UserContext()
}
