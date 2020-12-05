package com.reckue.oauth.configuration;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidFactory {

    private final UUID uuid = UUID.randomUUID();

    public String get() {
        return uuid.toString();
    }
}
