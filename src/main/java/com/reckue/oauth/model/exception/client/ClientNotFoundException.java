package com.reckue.oauth.model.exception.client;

import com.reckue.libs.exception.ModelNotFoundException;

public class ClientNotFoundException extends ModelNotFoundException {

    private final String message;

    public ClientNotFoundException() {
        message = "Client already exists.";
    }

    public ClientNotFoundException(String id) {
        message = "Client by id '" + id + "' already exists.";
    }
}
