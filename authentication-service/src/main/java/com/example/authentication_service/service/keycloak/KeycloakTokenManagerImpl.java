package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.configuration.keycloak.KeycloakFactory;
import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.service.web.WebClientService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakTokenManagerImpl implements KeycloakTokenManager<Map<String, String>> {
    protected final KeycloakFactory keycloakFactory;
    protected final WebClientService webClientService;
    protected final KeycloakEnvironment keycloakEnvironment;
    protected final KeycloakResourceManager keycloakResourceManager;

    public KeycloakTokenManagerImpl(KeycloakFactory keycloakFactory, WebClientService webClientService, KeycloakEnvironment keycloakEnvironment, KeycloakResourceManager keycloakResourceManager) {
        this.keycloakFactory = keycloakFactory;
        this.webClientService = webClientService;
        this.keycloakEnvironment = keycloakEnvironment;
        this.keycloakResourceManager = keycloakResourceManager;
    }

    @Override
    public Map<String, String> accessToken(String username, String password) {
        try (Keycloak keycloak = keycloakFactory.create(username, password)) {
            AccessTokenResponse token = keycloak.tokenManager().getAccessToken();
            return Map.of("access_token", token.getToken(), "refresh_token", token.getRefreshToken(), "expires_in", String.valueOf(token.getExpiresIn()), "refresh_expires_in", String.valueOf(token.getRefreshExpiresIn()));
        }
    }

    @Override
    public Map<String, String> accessToken(String refreshToken) {
        ClientRepresentation client = keycloakResourceManager.clientRepresentation(keycloakEnvironment.getAuthenticationRealmName(), keycloakEnvironment.getAuthenticationClientId());

        URI uri = URI.create(String.format("http://%s:%s/realms/%s/protocol/openid-connect/token", keycloakEnvironment.getKeycloakServerHost(), keycloakEnvironment.getKeycloakServerPort(), keycloakEnvironment.getAuthenticationRealmName()));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("client_id", List.of(client.getClientId()));
        map.put("client_secret", List.of(client.getSecret()));
        map.put("refresh_token", List.of(refreshToken));
        map.put("grant_type", List.of("refresh_token"));

        HttpEntity<MultiValueMap<String, String>> httpEntity = new RequestEntity<>(map, headers, HttpMethod.POST, uri);

        Map<String, String> response = webClientService.post(uri.toString(), httpEntity, Map.class).getBody();

        return Map.of("access_token", response.get("access_token"), "refresh_token", response.get("refresh_token"), "expires_in", String.valueOf(response.get("expires_in")), "refresh_expires_in", String.valueOf(response.get("refresh_expires_in")));
    }
}
