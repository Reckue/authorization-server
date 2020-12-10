package com.reckue.oauth.service;

public interface BaseUpdateService<T> {

    T updateById(String id, T entity);
}
