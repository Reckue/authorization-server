package com.reckue.oauth.mock;

import com.reckue.oauth.factory.PasswordCredentialsFactory;
import com.reckue.oauth.service.PasswordCredentialsValidatorService;
import lombok.RequiredArgsConstructor;
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
        when(service.exists(passwordCredentialsFactory.produce()))
                .thenReturn(true);
        doThrow(new RuntimeException("Password credentials already exists."))
                .when(service)
                .checkAlreadyExists(passwordCredentialsFactory.produce());
        return service;
    }
}
