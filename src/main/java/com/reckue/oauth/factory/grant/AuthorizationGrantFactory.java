package com.reckue.oauth.factory.grant;

import com.reckue.oauth.factory.PayloadFactory;
import com.reckue.oauth.model.internal.AuthorizationGrant;
import com.reckue.oauth.model.internal.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationGrantFactory implements PayloadFactory<AuthorizationGrant, Client> {

    private final RefreshTokenFactory refreshToken;
    private final AccessTokenFactory accessToken;

    @Override
    public AuthorizationGrant produce(Client client) {
        return new AuthorizationGrant(
                accessToken.produce(client.getSecret()),
                refreshToken.produce(client.getSecret()),
                client
        );
    }
}
