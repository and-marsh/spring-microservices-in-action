package com.optimagrowth.license.service

import com.optimagrowth.license.config.ServiceConfig
import com.optimagrowth.license.model.License
import com.optimagrowth.license.model.Organization
import com.optimagrowth.license.repository.LicenseRepository
import com.optimagrowth.license.service.client.OrganizationFeignClient
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.Locale
import java.util.UUID

@Service
class LicenseService(
    @field:Autowired val messages: MessageSource,
    @field:Autowired val licenseRepository: LicenseRepository,
    @field:Autowired val organizationFeignClient: OrganizationFeignClient,
    @field:Autowired val config: ServiceConfig
) {

    @CircuitBreaker(name = "licenseService", fallbackMethod = "buildFallbackLicenseList")
    fun getLicense(licenseId: String, organizationId: String, clientType: String? = null): License {
        val license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId)
            ?: throw IllegalArgumentException(
                messages.getMessage(
                    "license.search.error.message",
                    arrayOf(licenseId, organizationId),
                    DEFAULT_LOCALE
                )
            )

        val organization = clientType?.let { retrieveOrganizationInfo(organizationId, clientType) }

        return license.copy(
            comment = config.property,
            organizationName = organization?.name,
            contactName = organization?.contactName,
            contactPhone = organization?.contactPhone,
            contactEmail = organization?.contactEmail
        )
    }

    fun getLicensesByOrganization(organizationId: String): List<License> =
        licenseRepository.findByOrganizationId(organizationId)

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

    private fun retrieveOrganizationInfo(organizationId: String, clientType: String): Organization? =
        when (clientType) {
            "feign" -> {
                println("I am using the feign client")
                organizationFeignClient.getOrganization(organizationId)
            }

            "dead" -> {
                println("This client is dead")
                simulateTimeout()
            }

            else -> null
        }

    @Suppress("unused")
    private fun buildFallbackLicenseList(
        licenseId: String,
        organizationId: String,
        clientType: String?,
        t: Throwable
    ): License =
        License(
            licenseId = "0000000-00-00000",
            organizationId = organizationId,
            productName = "Sorry no licensing information currently available"
        )

    private fun simulateTimeout(): Nothing {
        try {
            Thread.sleep(2000);
            throw java.util.concurrent.TimeoutException();
        } catch (e: InterruptedException) {
            println(e.message);
            throw e
        }
    }

    companion object {

        val DEFAULT_LOCALE: Locale = Locale.ENGLISH
    }
}
