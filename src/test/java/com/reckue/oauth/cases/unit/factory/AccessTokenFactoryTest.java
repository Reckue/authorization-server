package com.reckue.oauth.cases.unit.factory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.factory.grant.AccessTokenFactory;
import com.reckue.oauth.factory.grant.JwtFactory;
import com.reckue.oauth.mock.MockUuidFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Class AccessTokenFactoryTest
 *
 * @author Kamila Meshcheryakova
 * created 25.12.2020
 */
@SpringBootTest(classes = {
        MockUuidFactory.class,
        AccessTokenFactory.class,
        JwtFactory.class
})
public class AccessTokenFactoryTest {

    static final String SECRET = "72f6517b-1b88-4a5f-a543-142805fa262d";
    @Autowired
    UuidFactory uuidFactory;
    @Autowired
    JwtFactory jwtFactory;
    @Autowired
    AccessTokenFactory accessTokenFactory;

    @Test
    void createJwtWithRequiredParams() {
        String expected = JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(Instant.now().plus(7, ChronoUnit.DAYS)))
                .withJWTId(uuidFactory.produce())
                .sign(Algorithm.HMAC256(SECRET));
        Assertions.assertEquals(expected, accessTokenFactory.produce(SECRET));
    }
}
