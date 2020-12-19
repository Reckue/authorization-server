package com.reckue.oauth.cases.integration;

import com.reckue.libs.exception.ReckueIllegalArgumentException;
import com.reckue.oauth.controller.credentials.PasswordCredentialsController;
import com.reckue.oauth.model.exceptions.PasswordCredentialsAlreadyExistsException;
import com.reckue.oauth.repository.PasswordCredentialsRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.reckue.oauth.factory.PasswordCredentialsFactoryMethods.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("staging")
public class PasswordCredentialsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordCredentialsController passwordCredentialsController;

    @Autowired
    private PasswordCredentialsRepository passwordCredentialsRepository;

    @Test
    @SneakyThrows
    public void createSamePasswordCredentialsTwice() {
        String body = buildPasswordCredentialsRequestAsString();
        this.mockMvc.perform(buildRegisterMockRequest(body))
                .andExpect(status().isOk())
                .andDo((result) -> retryCreatePasswordCredentialsRequest(body));
    }

    private void retryCreatePasswordCredentialsRequest(String body) throws Exception {
        this.mockMvc.perform(buildRegisterMockRequest(body))
                .andExpect(status().is4xxClientError())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof PasswordCredentialsAlreadyExistsException)
                );
    }

    @Test
    public void requestWithNullFieldsBody() throws Exception {
        String body = buildNullFieldsPasswordCredentialsRequestAsString();
        mockMvc.perform(buildRegisterMockRequest(body))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ReckueIllegalArgumentException));
    }
}
