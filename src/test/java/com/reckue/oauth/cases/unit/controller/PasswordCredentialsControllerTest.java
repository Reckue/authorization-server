package com.reckue.oauth.cases.unit.controller;

import com.reckue.oauth.controller.credentials.PasswordCredentialsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.reckue.oauth.factory.PasswordCredentialsFactoryMethods.buildPasswordCredentialsRequestAsString;
import static com.reckue.oauth.factory.PasswordCredentialsFactoryMethods.buildRegisterMockRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PasswordCredentialsController.class)
public class PasswordCredentialsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordCredentialsController passwordCredentialsController;

    @Test
    public void requestWithoutBody() throws Exception {
        mockMvc.perform(buildRegisterMockRequest())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void okRequest() throws Exception {
        String body = buildPasswordCredentialsRequestAsString();
        mockMvc.perform(buildRegisterMockRequest(body))
                .andExpect(status().isOk());
    }
}
