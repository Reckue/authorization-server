package com.reckue.oauth.service.logic;

import com.reckue.oauth.factory.grant.AuthorizationGrantFactory;
import com.reckue.oauth.factory.grant.BaseClientFactory;
import com.reckue.oauth.factory.grant.PasswordCredentialsFactory;
import com.reckue.oauth.model.internal.AuthorizationGrant;
import com.reckue.oauth.model.internal.Client;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;
import com.reckue.oauth.model.response.AuthorizationResponse;
import com.reckue.oauth.model.response.PasswordCredentialsResponse;
import com.reckue.oauth.service.store.impl.PasswordCredentialsStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordCredentialsService implements
        GrantDelivery<AuthorizationResponse, PasswordCredentialsRequest> {

    private final PasswordCredentialsStoreService passwordCredentialsStoreService;
    private final AuthorizationGrantFactory authorizationGrantFactory;
    private final BaseClientFactory baseClientFactory;
    private final PasswordCredentialsFactory passwordCredentialsFactory;

    @Override
    public AuthorizationResponse register(PasswordCredentialsRequest payload) {
        final Client client = baseClientFactory.produce();
        final PasswordCredentials passwordCredentials = passwordCredentialsFactory.produce(payload);
        final PasswordCredentials savedCredentials = passwordCredentialsStoreService.create(passwordCredentials);
        final PasswordCredentialsResponse responseCredentials = buildResponseCredentials(savedCredentials);
        return buildAuthorizationResponse(client, responseCredentials);
    }

    private AuthorizationResponse buildAuthorizationResponse(Client client, PasswordCredentialsResponse credentials) {
        final AuthorizationGrant authorizationGrant = authorizationGrantFactory.produce(client);
        return AuthorizationResponse.builder()
                .clientId(client.getId())
                .accessToken(authorizationGrant.getAccessToken())
                .refreshToken(authorizationGrant.getRefreshToken())
                .credentials(credentials).build();
    }

    private PasswordCredentialsResponse buildResponseCredentials(PasswordCredentials credentials) {
        return PasswordCredentialsResponse.builder()
                .id(credentials.getId())
                .username(credentials.getUsername())
                .email(credentials.getEmail()).build();
    }
}
