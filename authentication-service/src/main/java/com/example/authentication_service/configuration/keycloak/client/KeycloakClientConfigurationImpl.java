package com.example.authentication_service.configuration.keycloak.client;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class KeycloakClientConfigurationImpl implements KeycloakClientConfiguration {
    protected final KeycloakEnvironment keycloakEnvironment;

    public KeycloakClientConfigurationImpl(KeycloakEnvironment keycloakEnvironment) {
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public URI tokenUri(String realmName) {
        return URI.create(String.format("http://%s:%s/realms/%s/protocol/openid-connect/token", keycloakEnvironment.getKeycloakServerHost(), keycloakEnvironment.getKeycloakServerPort(), keycloakEnvironment.getAuthenticationRealmName()));
    }
}
