package com.example.authentication_service.service.user.registration;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.enumeration.KeycloakRealmGroup;
import com.example.authentication_service.model.keycloak.KeycloakUserModel;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.service.keycloak.KeycloakService;
import org.springframework.stereotype.Service;

@Service
public class UserKeycloakRegistrationService implements RegistrationService<KeycloakUserModel, KeycloakUserModel> {
    protected final KeycloakService<KeycloakUserModel, String> keycloakService;
    protected final KeycloakEnvironment keycloakEnvironment;

    public UserKeycloakRegistrationService(KeycloakService<KeycloakUserModel, String> keycloakService, KeycloakEnvironment keycloakEnvironment) {
        this.keycloakService = keycloakService;
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public KeycloakUserModel register(KeycloakUserModel registerRequest) {
        KeycloakUserModel keycloakUserModel = new KeycloakUserModel(registerRequest.getEmail(), registerRequest.getEmail(), registerRequest.getUuid(), registerRequest.getPassword(), keycloakEnvironment.getAuthenticationRealmName(),keycloakEnvironment.getAuthenticationClientId(), new String[]{KeycloakRealmGroup.ROLES_DEFAULT.name()});
        keycloakUserModel.setUserId(keycloakService.createUser(keycloakUserModel));
        keycloakService.joinGroup(keycloakUserModel);
        keycloakService.resetPassword(keycloakUserModel);
        return keycloakUserModel;
    }
}
