package com.example.authentication_service.service.keycloak;

public interface KeycloakService<S> {
    String createUser(S subject);
    void resetPassword(S subject);
    void joinGroup(S subject);
}
