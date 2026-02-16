package com.example.authentication_service.model.keycloak;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public class KeycloakRefreshTokenRequest {
    private String clientId;
    private String clientSecret;
    private String refreshToken;

    public KeycloakRefreshTokenRequest(Builder builder) {
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.refreshToken = builder.refreshToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public HttpEntity<MultiValueMap<String, String>> buildRequest() {
        validate();

        HttpHeaders headers = createHeaders();
        MultiValueMap<String, String> body = createBody();

        return new HttpEntity<>(body, headers);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    private MultiValueMap<String, String> createBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("refresh_token", refreshToken);
        body.add("grant_type", "refresh_token");

        return body;
    }

    private void validate() {
        List<String> missingFields = new ArrayList<>();

        if (clientId == null || clientId.isEmpty()) {
            missingFields.add("clientId");
        }
        if (clientSecret == null || clientSecret.isEmpty()) {
            missingFields.add("clientSecret");
        }
        if (refreshToken == null || refreshToken.isEmpty()) {
            missingFields.add("refreshToken");
        }

        if (!missingFields.isEmpty()) {
            throw new IllegalStateException(String.format("Missing required properties: %s", missingFields));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String clientId;
        private String clientSecret;
        private String refreshToken;

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public KeycloakRefreshTokenRequest build() {
            return new KeycloakRefreshTokenRequest(this);
        }
    }
}
