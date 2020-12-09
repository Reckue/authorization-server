package com.reckue.oauth.factory;

public interface AdditionalPayloadFactory<E, P> extends Factory {

    E produce(P payload, String[] additional);
}
