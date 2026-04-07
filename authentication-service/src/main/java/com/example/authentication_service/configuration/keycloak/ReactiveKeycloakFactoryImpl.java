package com.example.authentication_service.configuration.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.service.keycloak.ReactiveKeycloakResourceManager;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class ReactiveKeycloakFactoryImpl implements ReactiveKeycloakFactory {
    protected final KeycloakEnvironment keycloakEnvironment;
    protected final ReactiveKeycloakResourceManager keycloakResourceManager;

    public ReactiveKeycloakFactoryImpl(KeycloakEnvironment keycloakEnvironment, ReactiveKeycloakResourceManager keycloakResourceManager) {
        this.keycloakEnvironment = keycloakEnvironment;
        this.keycloakResourceManager = keycloakResourceManager;
    }

    @Override
    public Mono<Keycloak> create(String username, String password) {
        return keycloakResourceManager.clientRepresentation(keycloakEnvironment.getAuthenticationRealmName(), keycloakEnvironment.getAuthenticationClientId())
                .flatMap(client -> Mono.fromCallable(() -> KeycloakBuilder
                                .builder()
                                .serverUrl(String.format("http://%s:%s", keycloakEnvironment.getKeycloakServerHost(), keycloakEnvironment.getKeycloakServerPort()))
                                .realm(keycloakEnvironment.getAuthenticationRealmName())
                                .username(username)
                                .password(password)
                                .clientId(client.getClientId())
                                .clientSecret(client.getSecret())
                                .grantType(OAuth2Constants.PASSWORD)
                                .build())
                        .subscribeOn(Schedulers.boundedElastic()));
    }
}
