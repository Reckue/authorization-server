package com.reckue.oauth.repository;

import com.reckue.oauth.model.PasswordCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordCredentialsRepository extends MongoRepository<PasswordCredentials, String> {
}
