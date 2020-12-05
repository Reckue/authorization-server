package com.reckue.oauth.service.interfaces;

public interface CrudService<E> {

    E create(E entity);
    E get(String id);
    E update(String id, E entity);
    E delete(String id);
}
