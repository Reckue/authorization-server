package com.reckue.oauth.factory;

import java.util.Map;

/**
 * Interface MapPayloadFactory represents the factory with map as an argument.
 *
 * @author Kamila Meshcheryakova
 * created 23.12.2020
 */
public interface MapPayloadFactory<E, P> extends Factory {

    E produce(Map<E, P> map);
}
