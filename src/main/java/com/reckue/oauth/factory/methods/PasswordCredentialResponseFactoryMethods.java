package com.reckue.oauth.factory.methods;

import com.reckue.oauth.model.internal.AuthorizationGrant;
import com.reckue.oauth.model.internal.Client;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.model.response.AuthorizationResponse;
import com.reckue.oauth.model.response.PasswordCredentialsResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordCredentialResponseFactoryMethods {

    public static AuthorizationResponse buildAuthorizationResponse(Client client,
                                                                   PasswordCredentialsResponse credentials,
                                                                   AuthorizationGrant authorizationGrant) {
        return AuthorizationResponse.builder()
                .clientId(client.getId())
                .accessToken(authorizationGrant.getAccessToken())
                .refreshToken(authorizationGrant.getRefreshToken())
                .credentials(credentials).build();
    }

    public static PasswordCredentialsResponse buildResponseCredentials(PasswordCredentials credentials) {
        return PasswordCredentialsResponse.builder()
                .id(credentials.getId())
                .username(credentials.getUsername())
                .email(credentials.getEmail()).build();
    }
}
