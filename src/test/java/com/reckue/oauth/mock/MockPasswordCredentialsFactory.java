package com.reckue.oauth.mock;

import com.reckue.oauth.factory.IndependentFactory;
import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MockPasswordCredentialsFactory implements IndependentFactory<PasswordCredentials> {

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
