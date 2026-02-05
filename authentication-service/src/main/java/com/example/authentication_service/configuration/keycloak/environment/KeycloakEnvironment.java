package com.example.authentication_service.configuration.keycloak.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakEnvironment {
    private final String keycloakServerHost;
    private final int keycloakServerPort;
    private final String keycloakAdminUsername;
    private final String keycloakAdminPassword;

    public KeycloakEnvironment(@Value("${keycloak.host}") String keycloakServerHost, @Value("${keycloak.port}") int keycloakServerPort, @Value("${keycloak.admin.username}") String keycloakAdminUsername, @Value("${keycloak.admin.password}") String keycloakAdminPassword) {
        this.keycloakServerHost = keycloakServerHost;
        this.keycloakServerPort = keycloakServerPort;
        this.keycloakAdminUsername = keycloakAdminUsername;
        this.keycloakAdminPassword = keycloakAdminPassword;
    }

    public String getKeycloakServerHost() {
        return keycloakServerHost;
    }

    public int getKeycloakServerPort() {
        return keycloakServerPort;
    }

    public String getKeycloakAdminUsername() {
        return keycloakAdminUsername;
    }

    public String getKeycloakAdminPassword() {
        return keycloakAdminPassword;
    }
}
