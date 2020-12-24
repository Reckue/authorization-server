package com.reckue.oauth.cases.unit.service;

import com.reckue.oauth.factory.base.PasswordEncoder;
import com.reckue.oauth.factory.base.ReckueSaltFactory;
import com.reckue.oauth.factory.grant.*;
import com.reckue.oauth.mock.MockPasswordCredentialsFactory;
import com.reckue.oauth.mock.MockPasswordCredentialsStoreService;
import com.reckue.oauth.mock.MockUuidFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.model.response.AuthorizationResponse;
import com.reckue.oauth.model.response.PasswordCredentialsResponse;
import com.reckue.oauth.service.logic.PasswordCredentialsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reckue.oauth.factory.PasswordCredentialsFactoryMethods.buildPasswordCredentialsRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {
        PasswordCredentialsService.class,
        MockPasswordCredentialsStoreService.class,
        AuthorizationGrantFactory.class,
        RefreshTokenFactory.class,
        AccessTokenFactory.class,
        BaseClientFactory.class,
        PasswordCredentialsFactory.class,
        MockUuidFactory.class,
        PasswordEncoder.class,
        ReckueSaltFactory.class,
        MockPasswordCredentialsFactory.class
})
public class PasswordCredentialsServiceTest {

    @Autowired
    private PasswordCredentialsService passwordCredentialsService;

    @Autowired
    private BaseClientFactory baseClientFactory;

    @Autowired
    private MockPasswordCredentialsFactory mockPasswordCredentialsFactory;

    @Test
    public void successfullyRegistered() {
        AuthorizationResponse actual = passwordCredentialsService.register(buildPasswordCredentialsRequest());
        AuthorizationResponse expected = buildAuthorizationResponse();
        assertEquals(expected, actual);
    }

    public AuthorizationResponse buildAuthorizationResponse() {
        return AuthorizationResponse.builder()
                .clientId(baseClientFactory.produce().getId())
                .accessToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.e30.tq2AEDGE1ERz20ks2rBXujeuhbISthtdISjX1maJxcE")
                .refreshToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.e30.tq2AEDGE1ERz20ks2rBXujeuhbISthtdISjX1maJxcE")
                .credentials(buildPasswordCredentialsResponse())
                .build();
    }

    public PasswordCredentialsResponse buildPasswordCredentialsResponse() {
        PasswordCredentials passwordCredentials = mockPasswordCredentialsFactory.produce();
        return PasswordCredentialsResponse.builder()
                .id(passwordCredentials.getId())
                .email(passwordCredentials.getEmail())
                .username(passwordCredentials.getUsername())
                .build();
    }

    @Test
    public void tryToRegisterButAlreadyExists() {
        //TODO:: implements
    }

    @Test
    public void tryToRegisterButIllegalPassword() {
        //TODO:: implements
    }
}
