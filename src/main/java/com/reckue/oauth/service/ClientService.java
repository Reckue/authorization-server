package com.reckue.oauth.service;

import com.reckue.oauth.model.Client;
import com.reckue.oauth.service.interfaces.CrudService;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements CrudService<Client> {

    @Override
    public Client create(Client client) {
        return null;
    }

    @Override
    public Client get(String id) {
        return null;
    }

    @Override
    public Client update(String id, Client client) {
        return null;
    }

    @Override
    public Client delete(String id) {
        return null;
    }
}
