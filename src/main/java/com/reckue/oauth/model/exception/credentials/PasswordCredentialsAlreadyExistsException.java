package com.reckue.oauth.model.exception.credentials;

import com.reckue.libs.exception.ModelAlreadyExistsException;
import lombok.Getter;

@Getter
public class PasswordCredentialsAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public PasswordCredentialsAlreadyExistsException() {
        message = "Password credentials already exists.";
    }

    public PasswordCredentialsAlreadyExistsException(String id) {
        message = "Password credentials by id '" + id + "' already exists.";
    }
}
