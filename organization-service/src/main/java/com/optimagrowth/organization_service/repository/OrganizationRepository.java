package com.optimagrowth.organization_service.repository;

import com.optimagrowth.organization_service.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,String> {
    Optional<Organization> findById(UUID organizationId);
}
