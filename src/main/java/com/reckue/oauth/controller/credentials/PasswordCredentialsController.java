package com.reckue.oauth.controller.credentials;

import com.reckue.oauth.controller.CredentialsController;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;
import com.reckue.oauth.model.response.AuthorizationResponse;
import com.reckue.oauth.service.logic.PasswordCredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PasswordCredentialsController implements
        CredentialsController<AuthorizationResponse, PasswordCredentialsRequest> {

    private final PasswordCredentialsService passwordCredentialsService;

    @Override
    @PostMapping("/register")
    public AuthorizationResponse register(@RequestBody PasswordCredentialsRequest request) {
        return passwordCredentialsService.register(request);
    }
}
