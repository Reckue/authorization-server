package com.reckue.oauth.mock;

import com.reckue.oauth.factory.NoEncoderPasswordCredentialsFactory;
import com.reckue.oauth.model.exception.credentials.PasswordCredentialsAlreadyExistsException;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.service.check.PasswordCredentialsChecker;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.*;

@TestConfiguration
@RequiredArgsConstructor
public class MockPasswordCredentialsValidatorService {

    private final NoEncoderPasswordCredentialsFactory noEncoderPasswordCredentialsFactory;

    @Bean
    public PasswordCredentialsChecker getPasswordCredentialsValidatorService() {
        PasswordCredentialsChecker service = mock(PasswordCredentialsChecker.class);
        PasswordCredentials passwordCredentials = noEncoderPasswordCredentialsFactory.produce();
        when(service.exists(passwordCredentials)).thenReturn(true);
        Mockito.doThrow(new PasswordCredentialsAlreadyExistsException())
                .when(service)
                .checkAlreadyExists(passwordCredentials);
        return service;
    }
}
