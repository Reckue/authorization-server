package com.reckue.oauth.controller.credentials;

import com.reckue.oauth.controller.CredentialsController;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;
import com.reckue.oauth.model.response.AuthorizationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordCredentialsController implements CredentialsController<AuthorizationResponse, PasswordCredentialsRequest> {

    @Override
    @PostMapping("/register")
    public AuthorizationResponse register(PasswordCredentialsRequest request) {
        return null;
    }
}
