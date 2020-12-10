package com.reckue.oauth.mock;

import com.reckue.oauth.factory.PasswordCredentialsFactory;
import com.reckue.oauth.model.PasswordCredentials;
import com.reckue.oauth.service.PasswordCredentialsValidatorService;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.*;

@TestConfiguration
@RequiredArgsConstructor
public class MockPasswordCredentialsValidatorService {

    private final PasswordCredentialsFactory passwordCredentialsFactory;

    @Bean
    public PasswordCredentialsValidatorService getPasswordCredentialsValidatorService() {
        PasswordCredentialsValidatorService service = mock(PasswordCredentialsValidatorService.class);
        PasswordCredentials passwordCredentials = passwordCredentialsFactory.produce();
        when(service.exists(passwordCredentials)).thenReturn(true);
        Mockito.doThrow(new RuntimeException("Password credentials already exists."))
                .when(service)
                .checkAlreadyExists(passwordCredentials);
        return service;
    }
}
