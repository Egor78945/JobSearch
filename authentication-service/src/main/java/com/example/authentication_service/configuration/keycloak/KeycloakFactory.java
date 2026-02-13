package com.example.authentication_service.configuration.keycloak;

import org.keycloak.admin.client.Keycloak;

public interface KeycloakFactory {
    Keycloak create(String username, String password);
}
