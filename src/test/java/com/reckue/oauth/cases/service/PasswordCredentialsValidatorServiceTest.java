package com.reckue.oauth.cases.service;

import com.reckue.oauth.factory.PasswordCredentialsFactory;
import com.reckue.oauth.mock.MockPasswordCredentialsRepository;
import com.reckue.oauth.mock.MockUuidFactory;
import com.reckue.oauth.factory.base.ExampleMatcherFactory;
import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.model.PasswordCredentials;
import com.reckue.oauth.service.PasswordCredentialsValidatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        MockPasswordCredentialsRepository.class,
        PasswordCredentialsValidatorService.class,
        MongoExampleFactory.class,
        ExampleMatcherFactory.class,
        MockUuidFactory.class,
        PasswordCredentialsFactory.class
})
public class PasswordCredentialsValidatorServiceTest {

    @Autowired
    private PasswordCredentialsValidatorService passwordCredentialsValidatorService;

    @Autowired
    private PasswordCredentialsFactory passwordCredentialsFactory;

    @Test
    public void existsPasswordCredentials() {
        PasswordCredentials passwordCredentials = passwordCredentialsFactory.produce();
        boolean exists = passwordCredentialsValidatorService.exists(passwordCredentials);
        assertTrue(exists);
    }

    @Test
    public void passwordCredentialsAlreadyExists() {
        PasswordCredentials passwordCredentials = passwordCredentialsFactory.produce();
        Exception exception = assertThrows(RuntimeException.class, () ->
                passwordCredentialsValidatorService.checkAlreadyExists(passwordCredentials)
        );
        assertEquals("Password credentials already exists.", exception.getMessage());
    }

    @Test
    public void passwordCredentialsAlreadyExistsButWeChangePassword() {
        PasswordCredentials passwordCredentials = passwordCredentialsFactory.produce();
        passwordCredentials.setPassword("2222");
        assertDoesNotThrow(() -> passwordCredentialsValidatorService.checkAlreadyExists(passwordCredentials));
    }
}
