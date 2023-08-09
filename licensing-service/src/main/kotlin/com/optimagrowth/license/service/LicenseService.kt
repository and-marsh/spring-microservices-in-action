package com.optimagrowth.license.service

import com.optimagrowth.license.config.ServiceConfig
import com.optimagrowth.license.model.License
import com.optimagrowth.license.model.Organization
import com.optimagrowth.license.repository.LicenseRepository
import com.optimagrowth.license.service.client.OrganizationFeignClient
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import kotlin.random.Random
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

    @CircuitBreaker(name = "licenseService")
    fun getLicensesByOrganization(organizationId: String): List<License> {
        sleep()
        return licenseRepository.findByOrganizationId(organizationId)
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

    private fun retrieveOrganizationInfo(organizationId: String, clientType: String): Organization? =
        when (clientType) {
            "feign" -> {
                println("I am using the feign client")
                organizationFeignClient.getOrganization(organizationId)
            }

            else -> null
        }

    private fun randomlyRunLong() {
        val randomNum = Random.nextInt(3) + 1
        if (randomNum == 3) {
            sleep()
        }
    }

    private fun sleep() {
        try {
            Thread.sleep(2000);
            throw java.util.concurrent.TimeoutException();
        } catch (e: InterruptedException) {
            println(e.message);
        }
    }

    companion object {

        val DEFAULT_LOCALE: Locale = Locale.ENGLISH
    }
}
