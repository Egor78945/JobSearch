package com.example.authentication_service.configuration.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.service.keycloak.KeycloakResourceManager;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.stereotype.Component;

@Component
public class KeycloakFactoryImpl implements KeycloakFactory {
    protected final KeycloakEnvironment keycloakEnvironment;
    protected final KeycloakResourceManager keycloakResourceManager;

    public KeycloakFactoryImpl(KeycloakEnvironment keycloakEnvironment, KeycloakResourceManager keycloakResourceManager) {
        this.keycloakEnvironment = keycloakEnvironment;
        this.keycloakResourceManager = keycloakResourceManager;
    }

    @Override
    public Keycloak create(String username, String password) {
        ClientRepresentation client = keycloakResourceManager.clientRepresentation(keycloakEnvironment.getAuthenticationRealmName(), keycloakEnvironment.getAuthenticationClientId());
        return KeycloakBuilder
                .builder()
                .serverUrl(String.format("http://%s:%s", keycloakEnvironment.getKeycloakServerHost(), keycloakEnvironment.getKeycloakServerPort()))
                .realm(keycloakEnvironment.getAuthenticationRealmName())
                .username(username)
                .password(password)
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

}
