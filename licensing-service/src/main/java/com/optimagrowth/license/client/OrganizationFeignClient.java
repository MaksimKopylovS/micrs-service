package com.optimagrowth.license.client;

import com.optimagrowth.license.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(value = "licensing-service", url = "${spring.cloud.openfeign.client.config.licensing-service.url}")
public interface OrganizationFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/organization/{organizationId}", consumes = "application/json")
    Organization getOrganization(@PathVariable("organizationId") UUID organizationId);
}
