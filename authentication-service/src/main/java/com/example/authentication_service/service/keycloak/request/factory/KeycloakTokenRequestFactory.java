package com.example.authentication_service.service.keycloak.request.factory;

import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;

public interface KeycloakTokenRequestFactory {
    HttpEntity<MultiValueMap<String, String>> refreshTokenRequest(String clientId, String clientSecret, String refreshToken);
}
