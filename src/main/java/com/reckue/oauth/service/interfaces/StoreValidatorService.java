package com.reckue.oauth.service.interfaces;

public interface StoreValidatorService<T> {

    boolean exists(T entity);
    void checkAlreadyExists(T entity);
}
