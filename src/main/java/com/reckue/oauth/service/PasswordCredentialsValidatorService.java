package com.reckue.oauth.service;

import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.model.PasswordCredentials;
import com.reckue.oauth.repository.PasswordCredentialsRepository;
import com.reckue.oauth.service.interfaces.StoreValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordCredentialsValidatorService implements StoreValidatorService<PasswordCredentials> {

    private final PasswordCredentialsRepository passwordCredentialsRepository;

    private final MongoExampleFactory<PasswordCredentials> mongoExampleFactory;

    @Override
    public boolean exists(PasswordCredentials entity) {
        Example<PasswordCredentials> exampleEmail = mongoExampleFactory.produce(entity, "username");
        Example<PasswordCredentials> exampleUsername = mongoExampleFactory.produce(entity, "email");
        boolean existsEmail = passwordCredentialsRepository.exists(exampleEmail);
        boolean existsUsername = passwordCredentialsRepository.exists(exampleUsername);
        return existsEmail || existsUsername;
    }

    @Override
    public void checkAlreadyExists(PasswordCredentials entity) {
        if (this.exists(entity)) {
            throw new RuntimeException("Password credentials already exists.");
        }
    }
}
