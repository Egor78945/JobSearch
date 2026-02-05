package com.example.authentication_service.configuration.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.model.keycloak.KeycloakMasterRealmSubject;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.stereotype.Component;

@Component
public class KeycloakHandlerImplementation implements KeycloakHandler {
    protected Keycloak keycloak;
    protected KeycloakMasterRealmSubject keycloakMasterRealmSubject;
    protected KeycloakEnvironment keycloakEnvironment;

    public KeycloakHandlerImplementation(KeycloakMasterRealmSubject keycloakMasterRealmSubject, KeycloakEnvironment keycloakEnvironment) {
        this.keycloakMasterRealmSubject = keycloakMasterRealmSubject;
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public Keycloak getKeycloak() {
        return keycloak == null ?
                KeycloakBuilder
                        .builder()
                        .serverUrl(String.format("http://%s:%s", keycloakEnvironment.getKeycloakServerHost(), keycloakEnvironment.getKeycloakServerPort()))
                        .realm(keycloakMasterRealmSubject.getRealmName())
                        .username(keycloakEnvironment.getKeycloakAdminUsername())
                        .password(keycloakEnvironment.getKeycloakAdminPassword())
                        .clientId(keycloakMasterRealmSubject.getClientId())
                        .grantType(OAuth2Constants.PASSWORD)
                        .build() :
                keycloak;
    }
}
