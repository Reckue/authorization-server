package com.reckue.oauth.sandbox;

import com.reckue.oauth.model.response.AuthorizationResponse;
import com.reckue.oauth.service.logic.PasswordCredentialsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reckue.oauth.factory.PasswordCredentialsFactoryMethods.buildPasswordCredentialsRequest;

@SpringBootTest
public class Sandbox {

    @Autowired
    private PasswordCredentialsService passwordCredentialsService;

    @Test
    public void run() {
        AuthorizationResponse actual = passwordCredentialsService.register(buildPasswordCredentialsRequest());
        System.out.println(actual);
    }
}
