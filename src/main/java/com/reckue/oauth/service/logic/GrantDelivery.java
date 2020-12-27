package com.reckue.oauth.service.logic;

public interface GrantDelivery<E, P> {

    E register(P payload);
}
