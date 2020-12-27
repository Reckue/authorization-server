package com.reckue.oauth.factory;

public interface PayloadFactory<E, P> extends Factory {

    E produce(P payload);
}
