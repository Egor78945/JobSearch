package com.example.authentication_service.configuration.keycloak;

import org.keycloak.admin.client.Keycloak;
import reactor.core.publisher.Mono;

public interface ReactiveKeycloakFactory {
    Mono<Keycloak> create(String username, String password);
}
