package com.reckue.oauth.factory.grant;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.reckue.oauth.factory.PayloadFactory;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenFactory implements PayloadFactory<String, String> {

    @Override
    public String produce(String secret) {
        Algorithm algorithmHS = Algorithm.HMAC256(secret);
        return JWT.create().sign(algorithmHS);
    }
}
