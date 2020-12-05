package com.reckue.oauth.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecretFactory {

    private final String secret = UUID.randomUUID().toString();

    public String get() {
        return secret;
    }
}
