package com.example.authentication_service.service.user.authentication;

import com.example.authentication_service.model.keycloak.TokenResponse;
import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.service.AuthenticationService;
import com.example.authentication_service.service.keycloak.KeycloakTokenManager;
import org.springframework.stereotype.Service;

@Service
public class UserKeycloakAuthenticationService implements AuthenticationService<UserModel, String, TokenResponse> {
    protected final KeycloakTokenManager<TokenResponse> keycloakTokenManager;

    public UserKeycloakAuthenticationService(KeycloakTokenManager<TokenResponse> keycloakTokenManager) {
        this.keycloakTokenManager = keycloakTokenManager;
    }

    @Override
    public TokenResponse accessToken(UserModel loginModel) {
        return keycloakTokenManager.accessToken(loginModel.getEmail(), loginModel.getPassword());
    }

    @Override
    public TokenResponse refreshToken(String refreshTokenModel) {
        return keycloakTokenManager.accessToken(refreshTokenModel);
    }
}
