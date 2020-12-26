package com.reckue.oauth.factory;

import com.reckue.oauth.factory.base.PasswordEncoder;
import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.reckue.oauth.factory.PasswordCredentialsFactoryMethods.buildPasswordCredentialsRequest;

@Component
@RequiredArgsConstructor
public class DefaultEncoderPasswordCredentialsFactory implements IndependentFactory<PasswordCredentials> {

    private final UuidFactory uuidFactory;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PasswordCredentials produce() {
        PasswordCredentialsRequest request = buildPasswordCredentialsRequest();
        String encodedPassword = passwordEncoder.produce(request.getPassword());
        return PasswordCredentials.builder()
                .id(uuidFactory.produce())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(encodedPassword)
                .build();
    }
}
