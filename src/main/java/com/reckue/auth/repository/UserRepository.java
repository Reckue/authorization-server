package com.reckue.auth.repository;

import com.reckue.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface UserRepository configures the connection with PostgreSQL.
 *
 * @author Kamila Meshcheryakova
 * created 23.08.2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * This method is used to find the user by username in the database.
     *
     * @param username name of user
     * @return the object of class User with that username
     */
    Optional<User> findByUsername(String username);
}
