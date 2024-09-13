package com.optimagrowth.license.client;

import com.optimagrowth.license.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(value = "organization-service", url = "http://127.0.0.1:8073")
public interface OrganizationFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/organization/{organizationId}", consumes = "application/json")
    Organization getOrganization(@PathVariable("organizationId") UUID organizationId);
}
