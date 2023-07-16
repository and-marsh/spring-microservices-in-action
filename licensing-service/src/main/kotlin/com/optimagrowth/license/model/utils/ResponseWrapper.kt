package com.optimagrowth.license.model.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL

@JsonInclude(NON_NULL)
class ResponseWrapper(
    val data: Any,
    val metadata: Any,
    val errors: List<ErrorMessage>
)
