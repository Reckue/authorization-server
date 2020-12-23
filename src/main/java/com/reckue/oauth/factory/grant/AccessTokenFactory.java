package com.reckue.oauth.factory.grant;

import com.auth0.jwt.algorithms.Algorithm;
import com.reckue.oauth.factory.PayloadFactory;
import com.reckue.oauth.factory.base.UuidFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccessTokenFactory implements PayloadFactory<String, String> {

    @Override
    public String produce(String secret) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("issueAt", new Date());
        claims.put("algorithm", Algorithm.HMAC256(secret));
        claims.put("expiresAt", Date.from(Instant.now().plus(Period.ofDays(7))));
        claims.put("jwtId", new UuidFactory().produce());
        return new JwtFactory().produce(claims);
    }
}
