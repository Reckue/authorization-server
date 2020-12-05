package com.reckue.oauth.sandbox;

import com.reckue.oauth.configuration.ClientFactory;
import com.reckue.oauth.factory.AuthorizationGrantFactory;
import com.reckue.oauth.model.AuthorizationGrant;
import com.reckue.oauth.model.Client;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Sandbox {

    @Autowired
    private AuthorizationGrantFactory authorizationGrantFactory;

    @Autowired
    private ClientFactory clientFactory;

    @Test
    public void run() {
        Client client = clientFactory.get();
        AuthorizationGrant grant = authorizationGrantFactory.produce(client);
        System.out.println(grant.toString());
    }
}
