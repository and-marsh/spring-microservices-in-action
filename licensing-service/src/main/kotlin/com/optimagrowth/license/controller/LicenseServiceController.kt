package com.optimagrowth.license.controller

import com.optimagrowth.license.model.License
import com.optimagrowth.license.service.LicenseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["v1/organization/{organizationId}/license"])
class LicenseController(@field:Autowired private val licenseService: LicenseService) {

    @GetMapping(value = ["/{licenseId}"])
    fun getLicense(
        @PathVariable("organizationId") organizationId: String,
        @PathVariable("licenseId") licenseId: String
    ): ResponseEntity<License> {
        val license = licenseService.getLicense(licenseId, organizationId)
        license.add(
            linkTo(
                methodOn(LicenseController::class.java).getLicense(organizationId, licenseId)
            ).withSelfRel(),
            linkTo(
                methodOn(LicenseController::class.java).createLicense(license)
            ).withRel("createLicense"),
            linkTo(
                methodOn(LicenseController::class.java).updateLicense(license)
            ).withRel("updateLicense"),
            linkTo(
                methodOn(LicenseController::class.java).deleteLicense(licenseId)
            ).withRel("deleteLicense")
        )
        return ResponseEntity.ok(license)
    }

    @RequestMapping(value = ["/{licenseId}/{clientType}"], method = [GET])
    fun getLicensesWithClient(
        @PathVariable("organizationId") organizationId: String,
        @PathVariable("licenseId") licenseId: String,
        @PathVariable("clientType") clientType: String
    ): ResponseEntity<License> =
        ResponseEntity.ok(licenseService.getLicense(licenseId, organizationId, clientType))

    @PutMapping
    fun updateLicense(@RequestBody request: License): ResponseEntity<License> =
        ResponseEntity.ok(licenseService.updateLicense(request))

    @PostMapping
    fun createLicense(@RequestBody request: License): ResponseEntity<License> =
        ResponseEntity.ok(licenseService.createLicense(request))

    @DeleteMapping(value = ["/{licenseId}"])
    fun deleteLicense(@PathVariable("licenseId") licenseId: String): ResponseEntity<String> =
        ResponseEntity.ok(licenseService.deleteLicense(licenseId))
}
