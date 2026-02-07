package com.example.authentication_service.model.keycloak;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakMasterRealmUser extends KeycloakUser {
    public KeycloakMasterRealmUser(@Value("${keycloak.realm.master.name}") String realmName, @Value("${keycloak.realm.master.client.id}") String clientId, @Value("${keycloak.admin.username}") String username, @Value("${keycloak.admin.password}") String password) {
        super(username, username, password, realmName, clientId);
    }
}
