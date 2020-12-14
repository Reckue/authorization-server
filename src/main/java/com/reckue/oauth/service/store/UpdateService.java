package com.reckue.oauth.service.store;

public interface UpdateService<T> extends BaseUpdateService<T> {

    T pathById(String id, Object[] fields);
}
