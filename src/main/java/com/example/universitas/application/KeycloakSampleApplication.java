package com.example.universitas.application;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

public class KeycloakSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeycloakSampleApplication.class, args);
    }

    //Creating bean keycloakConfigResolver
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
