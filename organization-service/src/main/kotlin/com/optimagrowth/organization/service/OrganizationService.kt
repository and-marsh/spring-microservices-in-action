package com.optimagrowth.organization.service

import com.optimagrowth.organization.model.Organization
import com.optimagrowth.organization.repository.OrganizationRepository
import kotlin.jvm.optionals.getOrNull

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrganizationService(
    @field:Autowired private val repository: OrganizationRepository
) {

    fun findById(organizationId: String): Organization? =
        repository.findById(organizationId).getOrNull()

    fun create(organization: Organization): Organization {
        val newOrganization = organization.copy(id = UUID.randomUUID().toString())
        repository.save(newOrganization)

        return newOrganization
    }

    fun update(organization: Organization) {
        repository.save<Organization>(organization)
    }

    fun delete(organization: Organization) {
        organization.id?.let { id -> repository.deleteById(id) }
    }
}
