package com.optimagrowth.license.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.hateoas.RepresentationModel

@Entity
data class License(
    @Id
    @Column(name = "license_id", nullable = false) val licenseId: String? = null,
    @Column(name = "organization_id", nullable = false) val organizationId: String? = null,
    @Column(name = "product_name", nullable = false) val productName: String? = null,
    @Column(name = "license_type", nullable = false) val licenseType: String? = null,
    @Column(name = "description") val description: String? = null,
    @Column(name = "comment") val comment: String? = null,
) : RepresentationModel<License>()
