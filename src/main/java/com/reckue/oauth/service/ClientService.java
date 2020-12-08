package com.reckue.oauth.service;

import com.reckue.oauth.model.Client;
import com.reckue.oauth.service.interfaces.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements BaseCrudService<Client> {

    @Override
    public Client create(Client client) {
        return null;
    }

    @Override
    public Client findById(String id) {
        return null;
    }

    @Override
    public Client delete(String id) {
        return null;
    }
}
