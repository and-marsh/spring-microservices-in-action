package com.optimagrowth.organization.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "organizations")
data class Organization(
    @Id
    @Column(name = "organization_id") val id: String? = null,
    @Column(name = "name") val name: String? = null,
    @Column(name = "contact_name") val contactName: String? = null,
    @Column(name = "contact_email") val contactEmail: String? = null,
    @Column(name = "contact_phone") val contactPhone: String? = null
)
