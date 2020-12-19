package com.reckue.oauth.cases.unit.factory;

import com.reckue.oauth.factory.base.PasswordEncoder;
import com.reckue.oauth.factory.base.ReckueSaltFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReckueSaltFactory reckueSaltFactory;

    @Test
    @SneakyThrows
    public void validEncodedPassword() {
        String password = "12345678";
        String actual = passwordEncoder.produce(password);

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(reckueSaltFactory.produce());
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String expected = new String(hash);

        assertEquals(expected, actual);
    }
}
