package com.reckue.oauth.factory.base;

import com.reckue.oauth.factory.PayloadFactory;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

@Component
public class MongoExampleFactory<T> implements PayloadFactory<Example<T>, T> {

    private final ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id");

    @Override
    public Example<T> produce(T payload) {
        return Example.of(payload, matcher);
    }
}
