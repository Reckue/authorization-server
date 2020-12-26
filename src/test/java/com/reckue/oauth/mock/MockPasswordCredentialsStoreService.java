package com.reckue.oauth.mock;

import com.reckue.oauth.factory.grant.PasswordCredentialsFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.service.store.impl.PasswordCredentialsStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.reckue.oauth.factory.methods.PasswordCredentialsRequestFactoryMethods.buildPasswordCredentialsRequest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
@RequiredArgsConstructor
public class MockPasswordCredentialsStoreService {

    private final PasswordCredentialsFactory passwordCredentialsFactory;

    @Bean
    public PasswordCredentialsStoreService getPasswordCredentialsStoreService() {
        PasswordCredentialsStoreService service = mock(PasswordCredentialsStoreService.class);
        PasswordCredentials passwordCredentials = passwordCredentialsFactory.produce(buildPasswordCredentialsRequest());
        when(service.create(passwordCredentials)).thenReturn(passwordCredentials);
        return service;
    }
}
