package com.reckue.oauth.service;

public interface BaseCrdService<E> {

    E create(E entity);
    E findById(String id);
    E deleteById(String id);
}
