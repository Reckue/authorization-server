package com.reckue.oauth.cases.factory;

import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.factory.grant.BaseClientFactory;
import com.reckue.oauth.model.internal.Client;
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

    @Autowired
    private BaseClientFactory baseClientFactory;

    @Test
    public void createClientExampleWithIgnoredSecret() {
        final Client client = baseClientFactory.produce();
        String ignore = "secret";
        Example<Client> clientExample = mongoExampleFactory.produce(client, ignore);
        Set<String> ignored = clientExample.getMatcher().getIgnoredPaths();
        Assertions.assertArrayEquals(ignored.toArray(), new String[]{ignore});
    }

    @Test
    public void createDefaultClientExample() {
        final Client client = baseClientFactory.produce();
        Example<Client> clientExample = mongoExampleFactory.produce(client);
        Set<String> ignored = clientExample.getMatcher().getIgnoredPaths();
        Assertions.assertArrayEquals(ignored.toArray(), new String[]{"id"});
    }

    @Test
    public void createClientExampleFullIgnored() {
        final Client client = baseClientFactory.produce();
        String[] ignore = {"secret", "id"};
        Example<Client> clientExample = mongoExampleFactory.produce(client, ignore);
        Set<String> ignored = clientExample.getMatcher().getIgnoredPaths();
        Assertions.assertArrayEquals(ignored.toArray(), ignore);
    }
}
