package com.reckue.oauth.factory.grant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.reckue.libs.exception.ReckueIllegalArgumentException;
import com.reckue.oauth.factory.PayloadFactory;
import com.reckue.oauth.factory.base.UuidFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * Class JwtFactory represents the class for creating jwt.
 *
 * @author Kamila Meshcheryakova
 * created 23.12.2020
 */
@Component
public class JwtFactory implements PayloadFactory<String, Map<String, Object>> {

    @Autowired
    private UuidFactory uuidFactory;

    /**
     * The method allows to create any jwt with any params from map received from argument.
     *
     * @param claims map with params for jwt
     * @return string with jwt
     * @throws ReckueIllegalArgumentException in case when map doesn't contain the key "secret"
     */
    @Override
    public String produce(Map<String, Object> claims) {
        int days = claims.get("expiresInDays") != null ? (Integer) claims.get("expiresInDays") : 1;
        String secret = (String) claims.get("secret");
        if (secret == null) {
            throw new ReckueIllegalArgumentException("The map must contain the key - \"secret\"");
        }
        return JWT.create()
                .withIssuer((String) claims.get("issuer"))
                .withAudience((String[]) claims.get("audience"))
                .withSubject((String) claims.get("subject"))
                .withNotBefore((Date) claims.get("notBefore"))
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(Instant.now().plus(days, ChronoUnit.DAYS)))
                .withJWTId(uuidFactory.produce())
                .sign(Algorithm.HMAC256((String) claims.get("secret")));
    }
}
