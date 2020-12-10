package com.reckue.oauth.controller;

public interface CredentialsController<RES, REQ> {

    RES register(REQ request);
}
