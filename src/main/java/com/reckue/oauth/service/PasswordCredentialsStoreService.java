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

    @Override
    public PasswordCredentials create(PasswordCredentials entity) {
        return passwordCredentialsRepository.save(entity);
    }

    @Override
    public PasswordCredentials get(String id) {
        return passwordCredentialsRepository.findById(id).orElseThrow();
    }

    @Override
    public PasswordCredentials delete(String id) {
        PasswordCredentials entity = this.get(id);
        passwordCredentialsRepository.deleteById(id);
        return entity;
    }
}
