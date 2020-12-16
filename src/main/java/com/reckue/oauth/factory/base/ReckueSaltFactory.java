package com.reckue.oauth.factory.base;

import com.reckue.oauth.factory.IndependentFactory;
import org.springframework.stereotype.Component;

@Component
public class ReckueSaltFactory implements IndependentFactory<byte[]> {

    @Override
    public byte[] produce() {
        String saltString = "5c8ef3467ea1a1922ad2fa1b3aee29ce";
        return saltString.getBytes();
    }
}
