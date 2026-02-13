package com.example.authentication_service.configuration.keycloak.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakEnvironment {
    private final String keycloakServerHost;
    private final String authenticationRealmName;
    private final String authenticationClientId;
    private final int keycloakServerPort;

    public KeycloakEnvironment(@Value("${keycloak.host}") String keycloakServerHost, @Value("${keycloak.realm.test.name}") String authenticationRealmName, @Value("${keycloak.realm.test.client.id}") String authenticationClientId, @Value("${keycloak.port}") int keycloakServerPort) {
        this.keycloakServerHost = keycloakServerHost;
        this.authenticationRealmName = authenticationRealmName;
        this.authenticationClientId = authenticationClientId;
        this.keycloakServerPort = keycloakServerPort;
    }

    public String getKeycloakServerHost() {
        return keycloakServerHost;
    }

    public int getKeycloakServerPort() {
        return keycloakServerPort;
    }


    public String getAuthenticationRealmName() {
        return authenticationRealmName;
    }

    public String getAuthenticationClientId() {
        return authenticationClientId;
    }
}
