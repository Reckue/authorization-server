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

import static com.reckue.oauth.factory.methods.PasswordCredentialResponseFactoryMethods.buildAuthorizationResponse;
import static com.reckue.oauth.factory.methods.PasswordCredentialResponseFactoryMethods.buildResponseCredentials;

@Service
@RequiredArgsConstructor
public class PasswordCredentialsService implements
        GrantDelivery<AuthorizationResponse, PasswordCredentialsRequest> {

    private final PasswordCredentialsStoreService passwordCredentialsStoreService;
    private final AuthorizationGrantFactory authorizationGrantFactory;
    private final BaseClientFactory baseClientFactory;
    private final PasswordCredentialsFactory passwordCredentialsFactory;

    @Override
    public AuthorizationResponse register(PasswordCredentialsRequest request) {
        final PasswordCredentials passwordCredentials = passwordCredentialsFactory.produce(request);
        final PasswordCredentials savedCredentials = passwordCredentialsStoreService.create(passwordCredentials);
        final Client client = baseClientFactory.produce();
        final AuthorizationGrant authorizationGrant = authorizationGrantFactory.produce(client);
        final PasswordCredentialsResponse responseCredentials = buildResponseCredentials(savedCredentials);
        return buildAuthorizationResponse(client, responseCredentials, authorizationGrant);
    }
}
