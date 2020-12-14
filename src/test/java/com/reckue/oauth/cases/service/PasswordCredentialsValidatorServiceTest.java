package com.reckue.oauth.cases.service;

import com.reckue.oauth.factory.MockPasswordCredentialsFactory;
import com.reckue.oauth.mock.MockPasswordCredentialsRepository;
import com.reckue.oauth.mock.MockUuidFactory;
import com.reckue.oauth.factory.base.ExampleMatcherFactory;
import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.service.check.PasswordCredentialsChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reckue.oauth.utils.AlreadyExistsUtil.checkThrowReckuePasswordCredentialsAlreadyExists;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        MockPasswordCredentialsRepository.class,
        PasswordCredentialsChecker.class,
        MongoExampleFactory.class,
        ExampleMatcherFactory.class,
        MockUuidFactory.class,
        MockPasswordCredentialsFactory.class
})
public class PasswordCredentialsValidatorServiceTest {

    @Autowired
    private PasswordCredentialsChecker passwordCredentialsChecker;

    @Autowired
    private MockPasswordCredentialsFactory mockPasswordCredentialsFactory;

    @Test
    public void existsPasswordCredentials() {
        PasswordCredentials passwordCredentials = mockPasswordCredentialsFactory.produce();
        boolean exists = passwordCredentialsChecker.exists(passwordCredentials);
        assertTrue(exists);
    }

    @Test
    public void passwordCredentialsAlreadyExists() {
        PasswordCredentials passwordCredentials = mockPasswordCredentialsFactory.produce();
        checkThrowReckuePasswordCredentialsAlreadyExists(() ->
                passwordCredentialsChecker.checkAlreadyExists(passwordCredentials)
        );
    }

    @Test
    public void passwordCredentialsAlreadyExistsButWeChangePassword() {
        PasswordCredentials passwordCredentials = mockPasswordCredentialsFactory.produce();
        passwordCredentials.setPassword("2222");
        assertDoesNotThrow(() -> passwordCredentialsChecker.checkAlreadyExists(passwordCredentials));
    }
}
