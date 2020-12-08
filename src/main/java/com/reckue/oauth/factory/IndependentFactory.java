package com.reckue.oauth.factory;

public interface IndependentFactory<E> extends Factory {

    E produce();
}
