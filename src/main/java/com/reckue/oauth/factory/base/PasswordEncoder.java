package com.reckue.oauth.factory.base;

import com.reckue.oauth.factory.PayloadFactory;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

@Component
public class PasswordEncoder implements PayloadFactory<String, String> {

    @Override
    @SneakyThrows
    public String produce(String payload) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hash = md.digest(payload.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }
}
