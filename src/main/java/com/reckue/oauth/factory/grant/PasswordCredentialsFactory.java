package com.reckue.oauth.factory.grant;

import com.reckue.oauth.factory.PayloadFactory;
import com.reckue.oauth.factory.base.PasswordEncoder;
import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordCredentialsFactory implements
        PayloadFactory<PasswordCredentials, PasswordCredentialsRequest> {

    private final UuidFactory uuidFactory;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PasswordCredentials produce(PasswordCredentialsRequest payload) {
        String encodedPassword = passwordEncoder.produce(payload.getPassword());
        return PasswordCredentials.builder()
                .id(uuidFactory.produce())
                .email(payload.getEmail())
                .username(payload.getUsername())
                .password(encodedPassword).build();
    }
}
