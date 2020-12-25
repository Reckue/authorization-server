package com.reckue.oauth.cases.unit.factory;

import com.reckue.libs.exception.ReckueIllegalArgumentException;
import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.factory.grant.AccessTokenFactory;
import com.reckue.oauth.factory.grant.AuthorizationGrantFactory;
import com.reckue.oauth.factory.grant.JwtFactory;
import com.reckue.oauth.factory.grant.RefreshTokenFactory;
import com.reckue.oauth.mock.MockUuidFactory;
import com.reckue.oauth.model.internal.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {
        MockUuidFactory.class,
        AuthorizationGrantFactory.class,
        RefreshTokenFactory.class,
        AccessTokenFactory.class,
        JwtFactory.class
})
class AuthorizationGrantFactoryTest {

    @Autowired
    private AuthorizationGrantFactory authorizationGrantFactory;

    @Autowired
    private UuidFactory uuidFactory;

    @Test
    public void validateAuthorizationGrantSuccess() {
        Client validClient = new Client(uuidFactory.produce(), uuidFactory.produce());
        assertDoesNotThrow(() ->
                authorizationGrantFactory.produce(validClient));
    }

    @Test
    public void validateAuthorizationGrantFailedWithNullSecret() {
        Client noneValidClient = new Client("none", null);
        assertThrows(ReckueIllegalArgumentException.class, () ->
                authorizationGrantFactory.produce(noneValidClient));
    }

    @Test
    public void validateAuthorizationGrantFailedWithNullClient() {
        Assertions.assertThrows(ReckueIllegalArgumentException.class, () -> authorizationGrantFactory.produce(null));
    }
}