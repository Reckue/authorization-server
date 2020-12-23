package com.reckue.oauth.mock;

import com.reckue.oauth.factory.base.MongoExampleFactory;
import com.reckue.oauth.model.internal.PasswordCredentials;
import com.reckue.oauth.repository.PasswordCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;

import static org.mockito.Mockito.*;

@TestConfiguration
@RequiredArgsConstructor
public class MockPasswordCredentialsRepository {

    private final MockPasswordCredentialsFactory mockPasswordCredentialsFactory;

    private final MongoExampleFactory<PasswordCredentials> mongoExampleFactory;

    @Bean
    public PasswordCredentialsRepository getPasswordCredentialsRepository() {
        PasswordCredentialsRepository repository = mock(PasswordCredentialsRepository.class);
        mockExistsMethod(repository, mockPasswordCredentialsFactory.produce());
        mockFindByIdMethod(repository, mockPasswordCredentialsFactory.produce());
        mockDeleteByIdMethod(repository, mockPasswordCredentialsFactory.produce());
        return repository;
    }

    private void mockFindByIdMethod(PasswordCredentialsRepository repository, PasswordCredentials entity) {
        String id = entity.getId();
        when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));
    }

    private void mockDeleteByIdMethod(PasswordCredentialsRepository repository, PasswordCredentials entity) {
        String id = entity.getId();
        doNothing().when(repository).deleteById(id);
    }

    private void mockExistsMethod(PasswordCredentialsRepository repository, PasswordCredentials entity) {
        Example<PasswordCredentials> exampleEmail = mongoExampleFactory.produce(entity, "id", "username");
        Example<PasswordCredentials> exampleUsername = mongoExampleFactory.produce(entity, "id", "email");
        when(repository.exists(exampleEmail)).thenReturn(true);
        when(repository.exists(exampleUsername)).thenReturn(true);
    }
}

