package com.optimagrowth.organization_service.service;

import com.optimagrowth.organization_service.model.Organization;
import com.optimagrowth.organization_service.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }

    public Organization findById(UUID organizationId) {
        Optional<Organization> opt = repository.findById(organizationId);
        return (opt.isPresent()) ? opt.get() : null;
    }

    public Organization create(Organization organization){
        organization.setId( UUID.randomUUID());
        organization = repository.save(organization);
        return organization;
    }

    public void update(Organization organization){
        repository.save(organization);
    }

    public void delete(Organization organization){
        repository.deleteById(organization.getId().toString());
    }
}
