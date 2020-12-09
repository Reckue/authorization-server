package com.reckue.oauth.factory.base;

import com.reckue.oauth.factory.AdditionalPayloadFactory;

import com.reckue.oauth.factory.PayloadFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoExampleFactory<T> implements PayloadFactory<Example<T>, T>, AdditionalPayloadFactory<Example<T>, T> {

    private final ExampleMatcherFactory exampleMatcherFactory;

    @Override
    public Example<T> produce(T payload, String... ignorePaths) {
        ExampleMatcher matcher = exampleMatcherFactory.produce(ignorePaths);
        return Example.of(payload, matcher);
    }

    @Override
    public Example<T> produce(T payload) {
        return Example.of(payload, exampleMatcherFactory.produce());
    }
}
