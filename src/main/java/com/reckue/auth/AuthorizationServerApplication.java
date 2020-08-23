package com.reckue.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс AuthorizationServerApplication
 *
 * @author Kamila Meshcheryakova
 * created 23.08.2020
 */
//@EnableEurekaClient
@SpringBootApplication
public class AuthorizationServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }
}
