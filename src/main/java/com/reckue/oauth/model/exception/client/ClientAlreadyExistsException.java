package com.reckue.oauth.model.exception.client;

import com.reckue.libs.exception.ModelAlreadyExistsException;

public class ClientAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public ClientAlreadyExistsException() {
        message = "Client is not found.";
    }

    public ClientAlreadyExistsException(String id) {
        message = "Client by id '" + id + "' is not found.";
    }
}
