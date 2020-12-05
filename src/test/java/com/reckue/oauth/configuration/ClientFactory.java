package com.reckue.oauth.configuration;

import com.reckue.oauth.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientFactory {

    private final UuidFactory uuidFactory;
    private final SecretFactory secretFactory;

    public Client get() {
        return new Client(uuidFactory.get(), secretFactory.get());
    }
}
