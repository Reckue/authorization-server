package com.reckue.oauth.service.check;

public interface StoreChecker<T> {

    boolean exists(T entity);
    void checkAlreadyExists(T entity);
}
