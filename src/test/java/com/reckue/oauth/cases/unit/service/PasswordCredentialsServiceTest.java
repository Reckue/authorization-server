package com.reckue.oauth.cases.unit.service;

import com.reckue.oauth.factory.NoEncoderPasswordCredentialsFactory;
import com.reckue.oauth.factory.base.PasswordEncoder;
import com.reckue.oauth.factory.base.ReckueSaltFactory;
import com.reckue.oauth.factory.grant.*;
import com.reckue.oauth.mock.MockJwtFactory;
import com.reckue.oauth.mock.MockPasswordCredentialsStoreService;
import com.reckue.oauth.mock.MockUuidFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.model.response.AuthorizationResponse;
import com.reckue.oauth.model.response.PasswordCredentialsResponse;
import com.reckue.oauth.service.logic.PasswordCredentialsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reckue.oauth.factory.methods.PasswordCredentialsRequestFactoryMethods.buildPasswordCredentialsRequest;
import static com.reckue.oauth.utils.AlreadyExistsUtil.checkThrowReckueExceptionWithMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {
        PasswordCredentialsService.class,
        MockPasswordCredentialsStoreService.class,
        AuthorizationGrantFactory.class,
        RefreshTokenFactory.class,
        AccessTokenFactory.class,
        MockJwtFactory.class,
        BaseClientFactory.class,
        PasswordCredentialsFactory.class,
        MockUuidFactory.class,
        PasswordEncoder.class,
        ReckueSaltFactory.class,
        NoEncoderPasswordCredentialsFactory.class
})
public class PasswordCredentialsServiceTest {

    @Autowired
    private PasswordCredentialsService passwordCredentialsService;

    @Autowired
    private BaseClientFactory baseClientFactory;

    @Autowired
    private NoEncoderPasswordCredentialsFactory noEncoderPasswordCredentialsFactory;

    @Test
    public void successfullyRegistered() {
        AuthorizationResponse actual = passwordCredentialsService.register(buildPasswordCredentialsRequest());
        AuthorizationResponse expected = buildAuthorizationResponse();
        assertEquals(expected, actual);
    }

    @Test
    public void tryToRegisterButNullPasswordCredentialsRequest() {
        checkThrowReckueExceptionWithMessage(
                "PasswordCredentialsRequest is null",
                () -> passwordCredentialsService.register(null)
        );
    }

    //TODO:: Move to separate class
    public AuthorizationResponse buildAuthorizationResponse() {
        return AuthorizationResponse.builder()
                .clientId(baseClientFactory.produce().getId())
                .accessToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.e30.tq2AEDGE1ERz20ks2rBXujeuhbISthtdISjX1maJxcE")
                .refreshToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.e30.tq2AEDGE1ERz20ks2rBXujeuhbISthtdISjX1maJxcE")
                .credentials(buildPasswordCredentialsResponse())
                .build();
    }

    //TODO:: Move to separate class
    public PasswordCredentialsResponse buildPasswordCredentialsResponse() {
        PasswordCredentials passwordCredentials = noEncoderPasswordCredentialsFactory.produce();
        return PasswordCredentialsResponse.builder()
                .id(passwordCredentials.getId())
                .email(passwordCredentials.getEmail())
                .username(passwordCredentials.getUsername())
                .build();
    }
}
