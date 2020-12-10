package com.reckue.oauth.service.store;

import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.repository.PasswordCredentialsRepository;
import com.reckue.oauth.service.check.PasswordCredentialsChecker;
import com.reckue.oauth.service.BaseCrdService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordCredentialsStoreService implements BaseCrdService<PasswordCredentials> {

    private final PasswordCredentialsRepository passwordCredentialsRepository;
    private final PasswordCredentialsChecker passwordCredentialsValidatorService;

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
    public PasswordCredentials deleteById(String id) {
        PasswordCredentials entity = this.findById(id);
        passwordCredentialsRepository.deleteById(id);
        return entity;
    }
}
