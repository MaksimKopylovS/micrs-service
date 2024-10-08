package com.optimagrowth.organization_service.controller;

import com.optimagrowth.organization_service.model.Organization;
import com.optimagrowth.organization_service.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "v1/organization")
@CrossOrigin(origins = "*")
public class OrganizationController {
    private final OrganizationService service;

    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") UUID organizationId) {
        return ResponseEntity.ok(service.findById(organizationId));
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.PUT)
    public void updateOrganization(@PathVariable("organizationId") String id, @RequestBody Organization organization) {
        service.update(organization);
    }

    @PostMapping
    public ResponseEntity<Organization> saveOrganization(@RequestBody Organization organization) {
        return ResponseEntity.ok(service.create(organization));
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("id") String id, @RequestBody Organization organization) {
        service.delete(organization);
    }

    @GetMapping(value = "/home")
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return "Hello, Home";
    }

    @GetMapping(value = "/secured")
    public String secured() {
        return "Hello, secured";
    }

}
