package com.example.authentication_service.model.keycloak;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakMasterRealmSubject extends KeycloakSubject {
    public KeycloakMasterRealmSubject(@Value("${keycloak.realm.master.name}") String realmName, @Value("${keycloak.realm.master.client.id}") String clientId) {
        super(realmName, clientId);
    }
}
