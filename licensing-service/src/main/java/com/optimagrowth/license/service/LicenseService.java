package com.optimagrowth.license.service;

import com.optimagrowth.license.configuration.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LicenseService {

    private final MessageSource messages;
    private final LicenseRepository repository;
    private final ServiceConfig config;
    private final LicenseRepository licenseRepository;

    public LicenseService(MessageSource messages, LicenseRepository repository, ServiceConfig config, LicenseRepository licenseRepository) {
        this.messages = messages;
        this.repository = repository;
        this.config = config;
        this.licenseRepository = licenseRepository;
    }

    public License getLicense(String licenseId, String organizationId) {
        License license = repository.findByLicenseId(licenseId);
        if (license == null) {
            throw new IllegalArgumentException(
                    String.format(messages.getMessage("license.search.error.message", null, null),
                            licenseId, organizationId));

        }
        return license.withComment(config.getProperty());
    }

    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license) {
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId) {
        String responseMessage = null;
        License license = null;
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        responseMessage = String.format(messages.
                getMessage("license.delete.message", null, null), licenseId);
        return responseMessage;
    }
}
