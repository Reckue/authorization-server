package com.reckue.oauth.cases.store;

import com.reckue.oauth.factory.PasswordCredentialsFactory;
import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.repository.PasswordCredentialsRepository;
import com.reckue.oauth.service.store.PasswordCredentialsStoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reckue.oauth.utils.AlreadyExistsUtil.checkThrowReckuePasswordCredentialsAlreadyExists;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PasswordCredentialsStoreTest {

    @Autowired
    private PasswordCredentialsStoreService passwordCredentialsStoreService;

    @Autowired
    private PasswordCredentialsRepository passwordCredentialsRepository;

    @Autowired
    private MongoExampleFactory<PasswordCredentials> mongoExampleFactory;

    @Autowired
    private PasswordCredentialsFactory passwordCredentialsFactory;

    @Test
    public void createPasswordCredentials() {
        PasswordCredentials passwordCredentials = passwordCredentialsFactory.produce();
        long count = passwordCredentialsRepository.count(mongoExampleFactory.produce(passwordCredentials));
        if (count > 0) {
            checkThrowReckuePasswordCredentialsAlreadyExists(() ->
                    passwordCredentialsStoreService.create(passwordCredentials)
            );
        } else {
            PasswordCredentials saved = passwordCredentialsStoreService.create(passwordCredentials);
            assertEquals(passwordCredentials, saved);
        }
    }
}
