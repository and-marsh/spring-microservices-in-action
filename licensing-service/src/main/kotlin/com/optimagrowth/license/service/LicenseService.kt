package com.optimagrowth.license.service

import com.optimagrowth.license.config.ServiceConfig
import com.optimagrowth.license.model.License
import com.optimagrowth.license.repository.LicenseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.Locale
import java.util.UUID

@Service
class LicenseService(
    @field:Autowired val messages: MessageSource,
    @field:Autowired val licenseRepository: LicenseRepository,
    @field:Autowired val config: ServiceConfig
) {

    fun getLicense(licenseId: String, organizationId: String): License {
        val license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId)
            ?: throw IllegalArgumentException(
                messages.getMessage(
                    "license.search.error.message",
                    arrayOf(licenseId, organizationId),
                    DEFAULT_LOCALE
                )
            )

        return license.copy(comment = config.property)
    }

    fun createLicense(license: License): License {
        val newLicense = license.copy(licenseId = UUID.randomUUID().toString())
        licenseRepository.save(newLicense);

        return newLicense.copy(comment = config.property)
    }

    fun updateLicense(license: License): License {
        licenseRepository.save(license)

        return license.copy(comment = config.property)
    }

    fun deleteLicense(licenseId: String): String? {
        licenseRepository.deleteById(licenseId)

        return messages.getMessage("license.delete.message", arrayOf(licenseId), DEFAULT_LOCALE)
    }

    companion object {

        val DEFAULT_LOCALE: Locale = Locale.ENGLISH
    }
}
