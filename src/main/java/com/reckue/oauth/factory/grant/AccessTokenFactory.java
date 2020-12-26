package com.reckue.oauth.factory.grant;

import com.reckue.oauth.factory.PayloadFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AccessTokenFactory implements PayloadFactory<String, String> {

    private final JwtFactory jwtFactory;

    @Override
    public String produce(String secret) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("expiresInDays", 7);
        claims.put("secret", secret);
        return jwtFactory.produce(claims);
    }
}
