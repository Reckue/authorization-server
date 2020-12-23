package com.reckue.oauth.cases.unit.factory;

import com.reckue.libs.exception.ReckueIllegalArgumentException;
import com.reckue.oauth.factory.base.PasswordEncoder;
import com.reckue.oauth.factory.base.ReckueSaltFactory;
import com.reckue.oauth.factory.base.UuidFactory;
import com.reckue.oauth.factory.grant.PasswordCredentialsFactory;
import com.reckue.oauth.mock.MockUuidFactory;
import com.reckue.oauth.model.request.PasswordCredentialsRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reckue.oauth.factory.PasswordCredentialsFactoryMethods.buildPasswordCredentialsRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {
        PasswordCredentialsFactory.class,
        PasswordEncoder.class,
        MockUuidFactory.class,
        ReckueSaltFactory.class
})
public class PasswordCredentialsFactoryTest {

    @Autowired
    private PasswordCredentialsFactory passwordCredentialsFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UuidFactory uuidFactory;

    @Test
    public void tryProducePasswordCredentialsWithInvalidRequest() {
        PasswordCredentialsRequest passwordCredentialsRequest = PasswordCredentialsRequest.builder().build();
        Exception exception = assertThrows(ReckueIllegalArgumentException.class, () ->
                passwordCredentialsFactory.produce(passwordCredentialsRequest)
        );
        assertEquals("PasswordEncoder received null password.", exception.getMessage());
    }

    @Test
    public void producePasswordCredentialsAndCheckPassword() {
        final String expected = passwordEncoder.produce(buildPasswordCredentialsRequest().getPassword());
        final String actual = passwordCredentialsFactory.produce(buildPasswordCredentialsRequest()).getPassword();
        assertEquals(expected, actual);
    }

    @Test
    public void producePasswordCredentialsAndCheckId() {
        final String expected = uuidFactory.produce();
        final String actual = passwordCredentialsFactory.produce(buildPasswordCredentialsRequest()).getId();
        assertEquals(expected, actual);
    }

    @Test
    public void producePasswordCredentialsAndCheckUsername() {
        final String expected = buildPasswordCredentialsRequest().getUsername();
        final String actual = passwordCredentialsFactory.produce(buildPasswordCredentialsRequest()).getUsername();
        assertEquals(expected, actual);
    }

    @Test
    public void producePasswordCredentialsAndCheckEmail() {
        final String expected = buildPasswordCredentialsRequest().getEmail();
        final String actual = passwordCredentialsFactory.produce(buildPasswordCredentialsRequest()).getEmail();
        assertEquals(expected, actual);
    }
}
