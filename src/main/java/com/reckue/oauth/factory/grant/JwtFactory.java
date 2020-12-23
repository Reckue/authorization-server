package com.reckue.oauth.factory.grant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.reckue.oauth.factory.MapPayloadFactory;

import java.util.Date;
import java.util.Map;

/**
 * Class JwtFactory represents the class which create any jwt with any params from map received from argument.
 *
 * @author Kamila Meshcheryakova
 * created 23.12.2020
 */
public class JwtFactory implements MapPayloadFactory<String, Object> {

    @Override
    public String produce(Map<String, Object> claims) {
        return JWT.create()
                .withIssuer((String) claims.get("issuer"))
                .withAudience((String[]) claims.get("audience"))
                .withSubject((String) claims.get("subject"))
                .withNotBefore((Date) claims.get("notBefore"))
                .withIssuedAt((Date) claims.get("issueAt"))
                .withExpiresAt((Date) claims.get("expiresAt"))
                .withJWTId((String) claims.get("jwtId"))
                .sign((Algorithm) claims.get("algorithm"));
    }
}
