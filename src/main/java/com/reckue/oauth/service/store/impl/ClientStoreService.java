package com.reckue.oauth.service.store.impl;

import com.reckue.oauth.model.exception.client.ClientNotFoundException;
import com.reckue.oauth.model.internal.Client;
import com.reckue.oauth.repository.ClientRepository;
import com.reckue.oauth.service.check.ClientChecker;
import com.reckue.oauth.service.store.BaseCrdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientStoreService implements BaseCrdService<Client> {

    private final ClientRepository clientRepository;
    private final ClientChecker clientChecker;

    @Override
    public Client create(Client client) {
        clientChecker.checkAlreadyExists(client);
        return clientRepository.save(client);
    }

    @Override
    public Client findById(String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public Client deleteById(String id) {
        Client entity = this.findById(id);
        clientRepository.deleteById(id);
        return entity;
    }
}
