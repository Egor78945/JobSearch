package com.example.authentication_service.model.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakAdminModel extends KeycloakUserModel {
    protected final Keycloak keycloak;
    public KeycloakAdminModel(KeycloakEnvironment keycloakEnvironment, @Value("${keycloak.admin.username}") String username, @Value("${keycloak.admin.password}") String password, @Value("${keycloak.realm.master.name}") String realmName, @Value("${keycloak.realm.master.client.id}") String clientId) {
        super(username, username, password, realmName, clientId);
        keycloak = KeycloakBuilder
                .builder()
                .serverUrl(String.format("http://%s:%s", keycloakEnvironment.getKeycloakServerHost(), keycloakEnvironment.getKeycloakServerPort()))
                .realm(realmName)
                .username(username)
                .password(password)
                .clientId(clientId)
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

    public Keycloak getKeycloak() {
        return keycloak;
    }
}
