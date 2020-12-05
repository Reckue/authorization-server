package com.reckue.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationGrant {

    private String accessToken;
    private String refreshToken;
    private Client client;
}
