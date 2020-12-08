package com.reckue.oauth.factory.base;

import com.reckue.oauth.factory.IndependentFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidFactory implements IndependentFactory<String> {

    @Override
    public String produce() {
        return UUID.randomUUID().toString();
    }
}
