package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.configuration.keycloak.KeycloakFactory;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KeycloakTokenManagerImpl implements KeycloakTokenManager<Map<String, String>> {
    protected final KeycloakFactory keycloakFactory;

    public KeycloakTokenManagerImpl(KeycloakFactory keycloakFactory) {
        this.keycloakFactory = keycloakFactory;
    }

    @Override
    public Map<String, String> accessToken(String username, String password) {
        try (Keycloak keycloak = keycloakFactory.create(username, password)) {
            AccessTokenResponse token = keycloak.tokenManager().getAccessToken();
            return Map.of("access_token", token.getToken(), "refresh_token", token.getRefreshToken(), "expiresIn", String.valueOf(token.getExpiresIn()));
        }
    }
}
