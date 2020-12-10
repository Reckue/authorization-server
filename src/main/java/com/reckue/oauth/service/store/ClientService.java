package com.reckue.oauth.service.store;

import com.reckue.oauth.model.internal.Client;
import com.reckue.oauth.service.BaseCrdService;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements BaseCrdService<Client> {

    @Override
    public Client create(Client client) {
        return null;
    }

    @Override
    public Client findById(String id) {
        return null;
    }

    @Override
    public Client deleteById(String id) {
        return null;
    }
}
