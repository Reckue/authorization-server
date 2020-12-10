package com.reckue.oauth.model.request;

import lombok.Value;

@Value
public class PasswordCredentialsRequest {

    String email;
    String username;
    String password;
}
