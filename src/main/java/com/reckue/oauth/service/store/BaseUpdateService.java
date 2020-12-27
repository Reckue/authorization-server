package com.reckue.oauth.service.store;

public interface BaseUpdateService<T> {

    T updateById(String id, T entity);
}
