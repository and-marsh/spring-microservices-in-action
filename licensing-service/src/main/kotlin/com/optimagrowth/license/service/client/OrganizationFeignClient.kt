package com.optimagrowth.license.service.client

import com.optimagrowth.license.model.Organization
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET

@FeignClient("organization-service")
interface OrganizationFeignClient {

    @RequestMapping(method = [GET], value = ["/v1/organization/{organizationId}"], consumes = ["application/json"])
    fun getOrganization(@PathVariable("organizationId") organizationId: String): Organization?
}
