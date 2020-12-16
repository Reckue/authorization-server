package com.reckue.oauth.cases.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reckue.oauth.controller.credentials.PasswordCredentialsController;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PasswordCredentialsController.class)
public class PasswordCredentialsIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordCredentialsController passwordCredentialsController;

    @Test
    public void requestWithoutBody() throws Exception {
        MockHttpServletRequestBuilder mockRequest = post("/register").accept(MediaType.APPLICATION_JSON);
                mockMvc.perform(mockRequest)
               .andExpect(status().is4xxClientError());
    }


    @Test
    public void requestWithNullFieldsBody() throws Exception {
        PasswordCredentialsRequest passwordCredentials = buildNullFieldsPasswordCredentialsRequest();
        String body = objectMapper.writeValueAsString(passwordCredentials);

        MockHttpServletRequestBuilder mockRequest = post("/register")
                .content(body)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void okRequest() throws Exception {
        PasswordCredentialsRequest passwordCredentials = buildPasswordCredentialsRequest();
        String body = objectMapper.writeValueAsString(passwordCredentials);

        MockHttpServletRequestBuilder mockRequest = post("/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest).andExpect(status().isOk());
    }

    private PasswordCredentialsRequest buildNullFieldsPasswordCredentialsRequest() {
        return PasswordCredentialsRequest.builder()
                .username(null)
                .email(null)
                .password(null).build();
    }

    private PasswordCredentialsRequest buildPasswordCredentialsRequest() {
        return PasswordCredentialsRequest.builder()
                .username("333ok5")
                .email("o333k3")
                .password("o333k2").build();
    }
}
