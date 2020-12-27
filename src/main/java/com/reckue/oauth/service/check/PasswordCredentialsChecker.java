package com.reckue.oauth.service.check;

import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.model.exception.credentials.PasswordCredentialsAlreadyExistsException;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.repository.PasswordCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordCredentialsChecker implements StoreChecker<PasswordCredentials> {

    private final Logger logger = LoggerFactory.getLogger(PasswordCredentialsChecker.class);
    private final PasswordCredentialsRepository passwordCredentialsRepository;
    private final MongoExampleFactory<PasswordCredentials> mongoExampleFactory;

    @Override
    public boolean exists(PasswordCredentials entity) {
        boolean existsEmail = checkExistsAndLogMessage(entity, "email", "username");
        boolean existsUsername = checkExistsAndLogMessage(entity, "username", "email");
        return existsEmail || existsUsername;
    }

    private boolean checkExistsAndLogMessage(PasswordCredentials entity, String mainField, String ignore) {
        Example<PasswordCredentials> example = mongoExampleFactory.produce(entity, "id", ignore);
        boolean exists = passwordCredentialsRepository.exists(example);
        logger.info("Exists " + mainField + " :password pair = " + exists);
        return exists;
    }

    @Override
    public void checkAlreadyExists(PasswordCredentials entity) {
        if (this.exists(entity)) {
            throw new PasswordCredentialsAlreadyExistsException();
        }
    }
}