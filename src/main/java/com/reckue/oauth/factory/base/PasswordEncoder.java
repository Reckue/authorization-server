package com.reckue.oauth.factory.base;

import com.reckue.oauth.factory.PayloadFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
@RequiredArgsConstructor
public class PasswordEncoder implements PayloadFactory<String, String> {

    private final ReckueSaltFactory reckueSaltFactory;

    @Override
    @SneakyThrows
    public String produce(String password) {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(reckueSaltFactory.produce());
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }
}
