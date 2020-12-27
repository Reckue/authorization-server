package com.reckue.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
}
