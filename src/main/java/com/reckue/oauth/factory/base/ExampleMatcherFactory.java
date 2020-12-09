package com.reckue.oauth.factory.base;

import com.reckue.oauth.factory.IndependentFactory;
import com.reckue.oauth.factory.PayloadFactory;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

@Component
public class ExampleMatcherFactory implements IndependentFactory<ExampleMatcher>, PayloadFactory<ExampleMatcher, String[]> {

    @Override
    public ExampleMatcher produce() {
        return ExampleMatcher.matching().withIgnorePaths("id");
    }

    @Override
    public ExampleMatcher produce(String... payload) {
        return ExampleMatcher.matching().withIgnorePaths(payload);
    }
}
