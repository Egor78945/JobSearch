package com.example.authentication_service.model.keycloak;

public class KeycloakSubject {
    protected final String realmName;
    protected final String clientId;

    public KeycloakSubject(String realmName, String clientId) {
        this.realmName = realmName;
        this.clientId = clientId;
    }

    public String getRealmName() {
        return realmName;
    }

    public String getClientId() {
        return clientId;
    }
}
