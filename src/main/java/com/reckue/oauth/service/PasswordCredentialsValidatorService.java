package com.reckue.oauth.service;

import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.model.PasswordCredentials;
import com.reckue.oauth.repository.PasswordCredentialsRepository;
import com.reckue.oauth.service.interfaces.StoreValidatorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PasswordCredentialsValidatorService implements StoreValidatorService<PasswordCredentials> {

    Logger logger = LoggerFactory.getLogger(PasswordCredentialsValidatorService.class);

    private final PasswordCredentialsRepository passwordCredentialsRepository;

    private final MongoExampleFactory<PasswordCredentials> mongoExampleFactory;

    @Override
    public boolean exists(PasswordCredentials entity) {
        Example<PasswordCredentials> exampleEmail = mongoExampleFactory.produce(entity, "id", "username");
        Example<PasswordCredentials> exampleUsername = mongoExampleFactory.produce(entity, "id", "email");
        boolean existsEmail = passwordCredentialsRepository.exists(exampleEmail);
        logger.info("Exists email:password pair = " + existsEmail);
        boolean existsUsername = passwordCredentialsRepository.exists(exampleUsername);
        logger.info("Exists username:password pair = " + existsUsername);
        return existsEmail || existsUsername;
    }

    @Override
    public void checkAlreadyExists(PasswordCredentials entity) {
        if (this.exists(entity)) {
            throw new RuntimeException("Password credentials already exists.");
        }
    }
}
