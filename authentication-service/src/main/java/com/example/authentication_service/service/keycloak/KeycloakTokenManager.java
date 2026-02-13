package com.example.authentication_service.service.keycloak;

public interface KeycloakTokenManager<T> {
    T accessToken(String username, String password);
}
