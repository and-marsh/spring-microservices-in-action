package com.optimagrowth.license.utils

object UserContextHolder {

    private val userContext = ThreadLocal<UserContext?>()
    var context: UserContext?
        get() {
            if (userContext.get() == null) {
                userContext.set(UserContext())
            }
            return userContext.get()
        }
        set(context) {
            userContext.set(context)
        }
}
