package com.example.authentication_service.model.keycloak;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakAuthenticationRealmSubject extends KeycloakSubject {
    protected final String[] groups;

    public KeycloakAuthenticationRealmSubject(@Value("${keycloak.realm.test.name}") String realmName, @Value("${keycloak.realm.test.client.id}") String clientId, @Value("${keycloak.realm.test.groups}") String[] groups) {
        super(realmName, clientId);
        this.groups = groups;
    }

    public String[] getGroups() {
        return groups;
    }
}
