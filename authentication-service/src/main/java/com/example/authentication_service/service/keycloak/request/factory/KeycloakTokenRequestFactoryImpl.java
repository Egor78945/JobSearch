package com.example.authentication_service.service.keycloak.request.factory;

import com.example.authentication_service.model.keycloak.KeycloakRefreshTokenRequest;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class KeycloakTokenRequestFactoryImpl implements KeycloakTokenRequestFactory{
    @Override
    public HttpEntity<MultiValueMap<String, String>> refreshTokenRequest(String clientId, String clientSecret, String refreshToken) {
        return KeycloakRefreshTokenRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .refreshToken(refreshToken)
                .build()
                .buildRequest();
    }
}
