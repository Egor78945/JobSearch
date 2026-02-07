package com.example.authentication_service.configuration.keycloak.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakEnvironment {
    private final String keycloakServerHost;
    private final String realmName;
    private final String clientId;
    private final int keycloakServerPort;

    public KeycloakEnvironment(@Value("${keycloak.host}") String keycloakServerHost, @Value("${keycloak.realm.test.name}") String realmName, @Value("${keycloak.realm.test.client.id}") String clientId, @Value("${keycloak.port}") int keycloakServerPort) {
        this.keycloakServerHost = keycloakServerHost;
        this.realmName = realmName;
        this.clientId = clientId;
        this.keycloakServerPort = keycloakServerPort;
    }

    public String getKeycloakServerHost() {
        return keycloakServerHost;
    }

    public int getKeycloakServerPort() {
        return keycloakServerPort;
    }

    public String getRealmName() {
        return realmName;
    }

    public String getClientId() {
        return clientId;
    }
}
