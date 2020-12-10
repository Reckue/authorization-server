package com.reckue.oauth.cases.factory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.reckue.oauth.mock.MockUuidFactory;
import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.factory.grant.AccessTokenFactory;
import com.reckue.oauth.factory.grant.AuthorizationGrantFactory;
import com.reckue.oauth.factory.grant.RefreshTokenFactory;
import com.reckue.oauth.model.internal.AuthorizationGrant;
import com.reckue.oauth.model.internal.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {
        MockUuidFactory.class,
        AuthorizationGrantFactory.class,
        RefreshTokenFactory.class,
        AccessTokenFactory.class
})
class AuthorizationGrantFactoryTest {

    @Autowired
    private AuthorizationGrantFactory authorizationGrantFactory;

    @Autowired
    private UuidFactory uuidFactory;

    @Test
    public void validateAuthorizationGrant() {
        Client validClient = new Client(uuidFactory.produce(), uuidFactory.produce());
        Client noneValidClient = new Client("none", "valid");
        AuthorizationGrant validGrant = authorizationGrantFactory.produce(validClient);
        AuthorizationGrant noneValidGrant = authorizationGrantFactory.produce(noneValidClient);


        Algorithm algorithm = Algorithm.HMAC256(validClient.getSecret());
        JWTVerifier verifier = JWT.require(algorithm).build();


        assertDoesNotThrow(() ->
                verifier.verify(validGrant.getAccessToken())
        );
        assertThrows(JWTVerificationException.class, () ->
            verifier.verify(noneValidGrant.getAccessToken())
        );
    }
}