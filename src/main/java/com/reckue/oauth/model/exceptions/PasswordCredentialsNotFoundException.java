package com.reckue.oauth.model.exceptions;

import com.reckue.libs.exception.ModelNotFoundException;
import lombok.Getter;

@Getter
public class PasswordCredentialsNotFoundException extends ModelNotFoundException {

    private final String message;

    public PasswordCredentialsNotFoundException() {
        this.message = "Password credentials is not found.";
    }

    public PasswordCredentialsNotFoundException(String id) {
        this.message = "Password credentials by id '" + id + "' is not found";
    }
}
