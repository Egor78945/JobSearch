package com.example.authentication_service.service.keycloak;

import reactor.core.publisher.Mono;

public interface ReactiveKeycloakService<S> {
    Mono<String> createUser(S subject);
    Mono<Void> resetPassword(S subject);
    Mono<Void> joinGroup(S subject);
}
