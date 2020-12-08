package com.reckue.oauth.configuration;

import com.reckue.oauth.factory.base.UuidFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class MockUuidFactory {

    private final UUID uuid = UUID.randomUUID();

    @Bean
    public UuidFactory getUuidFactory() {
        UuidFactory factory = mock(UuidFactory.class);
        when(factory.produce()).thenReturn(uuid.toString());
        return factory;
    }
}
