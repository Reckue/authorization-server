package com.reckue.oauth.factory.methods;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Here we create MockHttpServletRequestBuilder's
 * for "/register" http POST methods.
 * Used into integration tests.
 *
 * @author Vladislav Lapshin
 * created 26.12.2020
 */
public class RegisterMockRequestFactoryMethods {

    public static MockHttpServletRequestBuilder getRegisterMockRequestBuilder(String body) {
        return post("/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    public static MockHttpServletRequestBuilder getRegisterMockRequestBuilder() {
        return post("/register").accept(MediaType.APPLICATION_JSON);
    }
}
