package com.reckue.oauth.cases.service;

import com.reckue.oauth.factory.PasswordCredentialsFactory;
import com.reckue.oauth.factory.base.ExampleMatcherFactory;
import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.mock.MockPasswordCredentialsRepository;
import com.reckue.oauth.mock.MockPasswordCredentialsValidatorService;
import com.reckue.oauth.mock.MockUuidFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        MockPasswordCredentialsRepository.class,
        MockPasswordCredentialsValidatorService.class,
        MongoExampleFactory.class,
        ExampleMatcherFactory.class,
        MockUuidFactory.class,
        PasswordCredentialsFactory.class
})
public class PasswordCredentialsStoreServiceTest {

}
