package com.reckue.oauth.cases.unit.factory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.reckue.libs.exception.ReckueIllegalArgumentException;
import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.factory.grant.JwtFactory;
import com.reckue.oauth.mock.MockUuidFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class JwtFactoryTest
 *
 * @author Kamila Meshcheryakova
 * created 25.12.2020
 */
@SpringBootTest(classes = {
        MockUuidFactory.class,
        JwtFactory.class
})
public class JwtFactoryTest {

    Map<String, Object> claims = new HashMap<>();
    @Autowired
    JwtFactory jwtFactory;
    @Autowired
    UuidFactory uuidFactory;

    @Test
    void createJwtWithAllParams() {
        Date date = new Date();
        claims.put("issuer", "auth-server");
        claims.put("audience", new String[]{"post-api", "account-api"});
        claims.put("subject", "miro");
        claims.put("notBefore", date);
        claims.put("expiresInDays", 1);
        claims.put("secret", "secret-key");

        String expected = JWT.create()
                .withIssuer((String) claims.get("issuer"))
                .withAudience((String[]) claims.get("audience"))
                .withSubject((String) claims.get("subject"))
                .withNotBefore((Date) claims.get("notBefore"))
                .withIssuedAt(date)
                .withExpiresAt(Date.from(Instant.now().plus((Integer) claims.get("expiresInDays"), ChronoUnit.DAYS)))
                .withJWTId(uuidFactory.produce())
                .sign(Algorithm.HMAC256((String) claims.get("secret")));
        Assertions.assertEquals(expected, jwtFactory.produce(claims));
    }

    @Test
    void createJwtWithMinimumParams() {
        claims.put("secret", "secret-key");
        String expected = JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .withJWTId(uuidFactory.produce())
                .sign(Algorithm.HMAC256((String) claims.get("secret")));
        Assertions.assertEquals(expected, jwtFactory.produce(claims));
    }

    @Test
    void createJwtWithRequiredParams() {
        claims.put("expiresInDays", 2);
        claims.put("secret", "secret-key");

        String expected = JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)))
                .withJWTId(uuidFactory.produce())
                .sign(Algorithm.HMAC256("secret-key"));
        Assertions.assertEquals(expected, jwtFactory.produce(claims));
    }

    @Test
    void createJwtWithEmptyParams() {
        Assertions.assertThrows(ReckueIllegalArgumentException.class, () -> jwtFactory.produce(claims));
    }
}
