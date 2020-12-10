package com.reckue.oauth.model.response;

import com.reckue.oauth.model.Credentials;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PasswordCredentialsResponse implements Credentials {

    String id;
    String email;
    String username;
    String password;
}
