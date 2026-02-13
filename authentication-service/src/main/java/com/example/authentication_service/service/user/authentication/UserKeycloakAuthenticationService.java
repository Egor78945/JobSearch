package com.example.authentication_service.service.user.authentication;

import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.service.AuthenticationService;
import com.example.authentication_service.service.keycloak.KeycloakTokenManager;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserKeycloakAuthenticationService implements AuthenticationService<UserModel, Map<String, String>> {
    protected final KeycloakTokenManager<Map<String, String>> keycloakTokenManager;

    public UserKeycloakAuthenticationService(KeycloakTokenManager<Map<String, String>> keycloakTokenManager) {
        this.keycloakTokenManager = keycloakTokenManager;
    }

    @Override
    public Map<String, String> authenticate(UserModel loginModel) {
        return keycloakTokenManager.accessToken(loginModel.getEmail(), loginModel.getPassword());
    }
}
