package com.example.authentication_service.configuration.keycloak.client;

import java.net.URI;

public interface KeycloakClientConfiguration {
    URI tokenUri(String realmName);
}
