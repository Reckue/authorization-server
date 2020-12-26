package com.reckue.oauth.factory.methods;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * Here we create static immutable models
 * of "PasswordCredentialsRequest" class.
 *
 * @author Vladislav Lapshin
 * created 26.12.2020
 */
public class PasswordCredentialsRequestFactoryMethods {

    /**
     * Need only to create JSON string into:
     * buildPasswordCredentialsRequestAsString()
     * buildNullFieldsPasswordCredentialsRequestAsString()
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     *  We fill this array using SecureRandom.
     *  After we use filled array of random bytes
     *  to create unique password.
     */
    private static final byte[] random = new byte[7];

    /**
     * Random unique password.
     * We use it into:
     * buildUniquePasswordCredentialsRequest()
     */
    private static final String password;

    static {
        new SecureRandom().nextBytes(random);
        password = new String(random, StandardCharsets.UTF_8);
    }

    public static String buildPasswordCredentialsRequestAsString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(buildUniquePasswordCredentialsRequest());
    }

    public static String buildNullFieldsPasswordCredentialsRequestAsString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(buildNullFieldsPasswordCredentialsRequest());
    }

    public static PasswordCredentialsRequest buildNullFieldsPasswordCredentialsRequest() {
        return PasswordCredentialsRequest.builder()
                .username(null)
                .email(null)
                .password(null).build();
    }

    public static PasswordCredentialsRequest buildPasswordCredentialsRequest() {
        return PasswordCredentialsRequest.builder()
                .username("hardelele")
                .email("hardelele@yahoo.com")
                .password("12345678").build();
    }

    public static PasswordCredentialsRequest buildUniquePasswordCredentialsRequest() {
        return PasswordCredentialsRequest.builder()
                .username("testPasswordCredentials")
                .email("test@mail.com")
                .password(password).build();
    }
}
