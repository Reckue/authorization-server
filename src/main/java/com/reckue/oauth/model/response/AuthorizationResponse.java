package com.reckue.oauth.model.response;

import com.reckue.oauth.model.Credentials;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthorizationResponse {

    String clientId;
    String accessToken;
    String refreshToken;
    Credentials credentials;
}
