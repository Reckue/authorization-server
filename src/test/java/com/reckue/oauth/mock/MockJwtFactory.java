package com.reckue.oauth.mock;

import com.reckue.oauth.factory.grant.JwtFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
@RequiredArgsConstructor
public class MockJwtFactory {

    private final String JWT = "eyJ0eXAiOiJKV1QiLCJhb" +
            "GciOiJIUzI1NiJ9.e30.tq2AEDGE1ERz20ks2rBX" +
            "ujeuhbISthtdISjX1maJxcE";

    @Bean
    public JwtFactory getJwtFactory() {
        JwtFactory factory = mock(JwtFactory.class);
        when(factory.produce(anyMap())).thenReturn(JWT);
        return factory;
    }
}
