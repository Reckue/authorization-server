package com.reckue.oauth.factory.grant;

import com.reckue.oauth.factory.IndependentFactory;
import com.reckue.oauth.model.internal.Client;
import org.springframework.stereotype.Component;

@Component
public class BaseClientFactory implements IndependentFactory<Client> {

    @Override
    public Client produce() {
        return new Client("cb92b0c5-6f11-4edd-8b5b-ad4c7d625982", "dfe4c033-da2c-417c-853d-98916dbc7de4");
    }
}
