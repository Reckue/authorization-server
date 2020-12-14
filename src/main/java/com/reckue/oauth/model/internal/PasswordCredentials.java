package com.reckue.oauth.model.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordCredentials {

    @Id
    private String id;
    private String email;
    private String username;
    private String password;
}
