package com.reckue.oauth.factory;

import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.reckue.oauth.factory.methods.PasswordCredentialsRequestFactoryMethods.buildPasswordCredentialsRequest;

@Component
@RequiredArgsConstructor
public class NoEncoderPasswordCredentialsFactory implements IndependentFactory<PasswordCredentials> {

    private final UuidFactory uuidFactory;

    @Override
    public PasswordCredentials produce() {
        PasswordCredentialsRequest request = buildPasswordCredentialsRequest();
        return PasswordCredentials.builder()
                .id(uuidFactory.produce())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }
}
