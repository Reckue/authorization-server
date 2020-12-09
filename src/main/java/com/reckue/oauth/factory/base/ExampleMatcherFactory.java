package com.reckue.oauth.factory.base;

import com.reckue.oauth.factory.IndependentFactory;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

@Component
public class ExampleMatcherFactory implements IndependentFactory<ExampleMatcher> {

    @Override
    public ExampleMatcher produce() {
        return ExampleMatcher.matching().withIgnorePaths("id");
    }
}
