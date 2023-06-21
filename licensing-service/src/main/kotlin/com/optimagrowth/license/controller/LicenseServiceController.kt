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
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Locale

@RestController
@RequestMapping(value = ["v1/organization/{organizationId}/license"])
class LicenseController(@field:Autowired private val licenseService: LicenseService) {

    @GetMapping(value = ["/{licenseId}"])
    fun getLicense(
        @PathVariable("organizationId") organizationId: String?,
        @PathVariable("licenseId") licenseId: String?
    ): ResponseEntity<License> {
        val license = licenseService.getLicense(licenseId, organizationId)
        license.add(
            linkTo(
                methodOn(LicenseController::class.java).getLicense(organizationId, license.licenseId)
            ).withSelfRel(),
            linkTo(
                methodOn(LicenseController::class.java).createLicense(organizationId, license, null)
            ).withRel("createLicense"),
            linkTo(
                methodOn(LicenseController::class.java).updateLicense(organizationId, license)
            ).withRel("updateLicense"),
            linkTo(
                methodOn(LicenseController::class.java).deleteLicense(organizationId, license.licenseId)
            ).withRel("deleteLicense")
        )
        return ResponseEntity.ok(license)
    }

    @PutMapping
    fun updateLicense(
        @PathVariable("organizationId") organizationId: String?,
        @RequestBody request: License?
    ): ResponseEntity<String> =
        ResponseEntity.ok(licenseService.updateLicense(request, organizationId))

    @PostMapping
    fun createLicense(
        @PathVariable("organizationId") organizationId: String?,
        @RequestBody request: License?,
        @RequestHeader(value = "Accept-Language", required = false) locale: Locale?
    ): ResponseEntity<String> =
        ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale))

    @DeleteMapping(value = ["/{licenseId}"])
    fun deleteLicense(
        @PathVariable("organizationId") organizationId: String?,
        @PathVariable("licenseId") licenseId: String?
    ): ResponseEntity<String> =
        ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId))
}
