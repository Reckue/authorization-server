package com.reckue.oauth.service;

import com.reckue.oauth.model.PasswordCredentials;
import com.reckue.oauth.repository.PasswordCredentialsRepository;
import com.reckue.oauth.service.interfaces.BaseCrudService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordCredentialsStoreService implements BaseCrudService<PasswordCredentials> {

    private final PasswordCredentialsRepository passwordCredentialsRepository;

    private final PasswordCredentialsValidatorService passwordCredentialsValidatorService;

    @Override
    public PasswordCredentials create(PasswordCredentials entity) {
        passwordCredentialsValidatorService.checkAlreadyExists(entity);
        return passwordCredentialsRepository.save(entity);
    }

    @Override
    public PasswordCredentials findById(String id) {
        return passwordCredentialsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find password credentials by id"));
    }

    @Override
    public PasswordCredentials delete(String id) {
        PasswordCredentials entity = this.findById(id);
        passwordCredentialsRepository.deleteById(id);
        return entity;
    }
}
