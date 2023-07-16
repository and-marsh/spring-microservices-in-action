package com.optimagrowth.license.model.utils

import org.springframework.http.HttpStatus

class RestErrorList(val status: HttpStatus, vararg errors: ErrorMessage?) : ArrayList<ErrorMessage?>() {

    companion object {

        /** Generated Serial Version */
        private const val serialVersionUID = -721424777198115589L
    }
}
