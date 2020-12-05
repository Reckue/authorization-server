package com.reckue.oauth.factory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenFactory implements FactoryService<String, String> {

    @Override
    public String produce(String secret) {
        Algorithm algorithmHS = Algorithm.HMAC256(secret);
        return JWT.create().sign(algorithmHS);
    }
}
