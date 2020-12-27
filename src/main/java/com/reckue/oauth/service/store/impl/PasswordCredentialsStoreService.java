package com.reckue.oauth.service.store.impl;

import com.reckue.oauth.model.exception.credentials.PasswordCredentialsNotFoundException;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.repository.PasswordCredentialsRepository;
import com.reckue.oauth.service.check.PasswordCredentialsChecker;
import com.reckue.oauth.service.store.BaseCrdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordCredentialsStoreService implements BaseCrdService<PasswordCredentials> {

    private final PasswordCredentialsRepository passwordCredentialsRepository;
    private final PasswordCredentialsChecker passwordCredentialsChecker;

    @Override
    public PasswordCredentials create(PasswordCredentials entity) {
        passwordCredentialsChecker.checkAlreadyExists(entity);
        return passwordCredentialsRepository.save(entity);
    }

    @Override
    public PasswordCredentials findById(String id) {
        return passwordCredentialsRepository.findById(id)
                .orElseThrow(() -> new PasswordCredentialsNotFoundException(id));
    }

    @Override
    public PasswordCredentials deleteById(String id) {
        PasswordCredentials entity = this.findById(id);
        passwordCredentialsRepository.deleteById(id);
        return entity;
    }
}
