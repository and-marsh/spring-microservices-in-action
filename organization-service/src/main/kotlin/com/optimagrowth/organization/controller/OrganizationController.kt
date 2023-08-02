package com.optimagrowth.organization.controller

import com.optimagrowth.organization.model.Organization
import com.optimagrowth.organization.service.OrganizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["v1/organization"])
class OrganizationController(
    @field:Autowired private val service: OrganizationService
) {

    @GetMapping(value = ["/{organizationId}"])
    fun getOrganization(@PathVariable("organizationId") organizationId: String): ResponseEntity<Organization> =
        ResponseEntity.ok(service.findById(organizationId))

    @PutMapping(value = ["/{organizationId}"])
    fun updateOrganization(@PathVariable("organizationId") id: String, @RequestBody organization: Organization) {
        service.update(organization)
    }

    @PostMapping
    fun saveOrganization(@RequestBody organization: Organization): ResponseEntity<Organization> =
        ResponseEntity.ok(service.create(organization))

    @DeleteMapping(value = ["/{organizationId}"])
    @ResponseStatus(NO_CONTENT)
    fun deleteOrganization(@PathVariable("organizationId") id: String?, @RequestBody organization: Organization) {
        service.delete(organization)
    }
}
