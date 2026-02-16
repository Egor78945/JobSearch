package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.configuration.keycloak.KeycloakFactory;
import com.example.authentication_service.configuration.keycloak.client.KeycloakClientConfiguration;
import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.model.keycloak.TokenResponse;
import com.example.authentication_service.service.keycloak.request.factory.KeycloakTokenRequestFactory;
import com.example.authentication_service.service.web.WebClientService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.net.URI;

@Service
public class KeycloakTokenManagerImpl implements KeycloakTokenManager<TokenResponse> {
    protected final KeycloakFactory keycloakFactory;
    protected final WebClientService webClientService;
    protected final KeycloakEnvironment keycloakEnvironment;
    protected final KeycloakClientConfiguration clientConfiguration;
    protected final KeycloakResourceManager keycloakResourceManager;
    protected final KeycloakTokenRequestFactory tokenRequestFactory;

    public KeycloakTokenManagerImpl(KeycloakFactory keycloakFactory, WebClientService webClientService, KeycloakEnvironment keycloakEnvironment, KeycloakClientConfiguration clientConfiguration, KeycloakResourceManager keycloakResourceManager, KeycloakTokenRequestFactory tokenRequestFactory) {
        this.keycloakFactory = keycloakFactory;
        this.webClientService = webClientService;
        this.keycloakEnvironment = keycloakEnvironment;
        this.clientConfiguration = clientConfiguration;
        this.keycloakResourceManager = keycloakResourceManager;
        this.tokenRequestFactory = tokenRequestFactory;
    }

    @Override
    public TokenResponse accessToken(String username, String password) {
        try (Keycloak keycloak = keycloakFactory.create(username, password)) {
            AccessTokenResponse token = keycloak.tokenManager().getAccessToken();
            return new TokenResponse(token.getToken(), token.getRefreshToken(), token.getExpiresIn(), token.getRefreshExpiresIn());
        }
    }

    @Override
    public TokenResponse accessToken(String refreshToken) {
        ClientRepresentation client = keycloakResourceManager.clientRepresentation(keycloakEnvironment.getAuthenticationRealmName(), keycloakEnvironment.getAuthenticationClientId());

        URI uri = clientConfiguration.tokenUri(keycloakEnvironment.getAuthenticationRealmName());

        HttpEntity<MultiValueMap<String, String>> request = tokenRequestFactory.refreshTokenRequest(client.getClientId(), client.getSecret(), refreshToken);

        ResponseEntity<TokenResponse> response = webClientService.post(uri.toString(), request, TokenResponse.class);

        return response.getBody();
    }
}
