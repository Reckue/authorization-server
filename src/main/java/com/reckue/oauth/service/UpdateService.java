package com.reckue.oauth.service;

public interface UpdateService<T> extends BaseUpdateService<T> {

    T pathById(String id, Object[] fields);
}
