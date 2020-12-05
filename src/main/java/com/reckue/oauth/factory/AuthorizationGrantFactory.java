package com.reckue.oauth.factory;

import com.reckue.oauth.model.AuthorizationGrant;
import com.reckue.oauth.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationGrantFactory implements FactoryService<AuthorizationGrant, Client> {

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
