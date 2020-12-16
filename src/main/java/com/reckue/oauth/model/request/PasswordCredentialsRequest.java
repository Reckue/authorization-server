package com.reckue.oauth.model.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PasswordCredentialsRequest {

    String email;
    String username;
    String password;
}
