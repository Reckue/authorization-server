package com.reckue.oauth.factory;

import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.Set;

@SpringBootTest
public class MongoExampleFactoryTest {

    @Autowired
    private MongoExampleFactory<Client> mongoExampleFactory;

    private final Client client = new Client("13", "22");

    @Test
    public void createClientExampleWithIgnoredSecret() {
        String ignore = "secret";
        Example<Client> clientExample = mongoExampleFactory.produce(client, ignore);
        Set<String> ignored = clientExample.getMatcher().getIgnoredPaths();
        Assertions.assertArrayEquals(ignored.toArray(), new String[]{ignore});
    }

    @Test
    public void createDefaultClientExample() {
        Example<Client> clientExample = mongoExampleFactory.produce(client);
        Set<String> ignored = clientExample.getMatcher().getIgnoredPaths();
        Assertions.assertArrayEquals(ignored.toArray(), new String[]{"id"});
    }

    @Test
    public void createClientExampleFullIgnored() {
        String[] ignore = {"secret", "id"};
        Example<Client> clientExample = mongoExampleFactory.produce(client, ignore);
        Set<String> ignored = clientExample.getMatcher().getIgnoredPaths();
        Assertions.assertArrayEquals(ignored.toArray(), ignore);
    }
}
