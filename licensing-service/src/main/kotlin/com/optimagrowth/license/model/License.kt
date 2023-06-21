package com.optimagrowth.license.model

import org.jetbrains.annotations.NotNull
import org.springframework.hateoas.RepresentationModel

data class License(
    @NotNull
    val id: Int,
    val licenseId: String? = null,
    val description: String? = null,
    val organizationId: String? = null,
    val productName: String? = null,
    val licenseType: String? = null
) : RepresentationModel<License>()
