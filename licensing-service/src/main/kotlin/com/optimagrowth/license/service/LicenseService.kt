package com.optimagrowth.license.service

import com.optimagrowth.license.model.License
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.Locale
import java.util.Random

@Service
class LicenseService(@field:Autowired val messages: MessageSource) {

    fun getLicense(licenseId: String?, organizationId: String?): License =
        License(
            id = Random().nextInt(1000),
            licenseId = licenseId,
            organizationId = organizationId,
            description = "Software product",
            productName = "Ostock",
            licenseType = "full"
        )

    fun createLicense(license: License?, organizationId: String?, locale: Locale?): String? =
        license?.run {
            val newLicense = license.copy(organizationId = organizationId)
            messages.getMessage("license.create.message", arrayOf(newLicense.toString()), locale ?: DEFAULT_LOCALE)
        }

    fun updateLicense(license: License?, organizationId: String?): String? =
        license?.run {
            val updatedLicense = license.copy(organizationId = organizationId)

            messages.getMessage("license.update.message", arrayOf(updatedLicense.toString()), DEFAULT_LOCALE)
        }

    fun deleteLicense(licenseId: String?, organizationId: String?): String? =
        licenseId?.run {
            messages.getMessage("license.delete.message", arrayOf(licenseId, organizationId), DEFAULT_LOCALE)
        }

    companion object {

        val DEFAULT_LOCALE: Locale = Locale.ENGLISH
    }
}
