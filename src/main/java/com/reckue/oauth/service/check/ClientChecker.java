package com.reckue.oauth.service.check;

import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.model.exception.client.ClientAlreadyExistsException;
import com.reckue.oauth.model.internal.Client;
import com.reckue.oauth.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientChecker implements StoreChecker<Client> {

    private final Logger logger = LoggerFactory.getLogger(PasswordCredentialsChecker.class);
    private final ClientRepository clientRepository;
    private final MongoExampleFactory<Client> mongoExampleFactory;

    @Override
    public boolean exists(Client entity) {
        Example<Client> example = mongoExampleFactory.produce(entity, "secret");
        boolean exists = clientRepository.exists(example);
        logger.info("Exists Client = " + exists);
        return exists;
    }

    @Override
    public void checkAlreadyExists(Client entity) {
        if (this.exists(entity)) {
            throw new ClientAlreadyExistsException();
        }
    }
}
