package com.reckue.oauth.factory;

public interface FactoryService<E, P> {

    E produce(P payload);
}
