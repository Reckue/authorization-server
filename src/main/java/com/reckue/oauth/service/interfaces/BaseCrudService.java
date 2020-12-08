package com.reckue.oauth.service.interfaces;

public interface BaseCrudService<E> {

    E create(E entity);
    E findById(String id);
    E delete(String id);
}
