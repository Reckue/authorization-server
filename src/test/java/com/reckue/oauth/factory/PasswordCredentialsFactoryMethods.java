package com.reckue.oauth.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PasswordCredentialsFactoryMethods {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final byte[] bytes = new byte[7];
    private static final String password;

    static {
        new SecureRandom().nextBytes(bytes);
        password = new String(bytes, StandardCharsets.UTF_8);
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

    public static MockHttpServletRequestBuilder buildRegisterMockRequest(String body) {
        return post("/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    public static MockHttpServletRequestBuilder buildRegisterMockRequest() {
        return post("/register").accept(MediaType.APPLICATION_JSON);
    }
}
