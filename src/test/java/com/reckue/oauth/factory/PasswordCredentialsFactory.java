package com.reckue.oauth.factory;

import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.model.PasswordCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class PasswordCredentialsFactory implements IndependentFactory<PasswordCredentials> {

    private final UuidFactory uuidFactory;

    @Override
    public PasswordCredentials produce() {
        return new PasswordCredentials(
                uuidFactory.produce(),
                "hardelele@yahoo.com",
                "hardelele",
                "12345678"
        );
    }
}
