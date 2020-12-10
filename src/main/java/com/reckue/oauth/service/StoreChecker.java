package com.reckue.oauth.service;

public interface StoreChecker<T> {

    boolean exists(T entity);
    void checkAlreadyExists(T entity);
}
