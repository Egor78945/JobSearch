package com.example.authentication_service.service.user.registration;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.enumeration.KeycloakRealmGroup;
import com.example.authentication_service.model.keycloak.KeycloakUser;
import com.example.authentication_service.model.user.security.UserAuthenticationModel;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.service.keycloak.KeycloakService;
import org.springframework.stereotype.Service;

@Service
public class UserKeycloakRegistrationService implements RegistrationService<UserAuthenticationModel> {
    protected final KeycloakService<KeycloakUser> keycloakService;
    protected final KeycloakEnvironment keycloakEnvironment;

    public UserKeycloakRegistrationService(KeycloakService<KeycloakUser> keycloakService, KeycloakEnvironment keycloakEnvironment) {
        this.keycloakService = keycloakService;
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public void register(UserAuthenticationModel registerRequest) {
        KeycloakUser keycloakUser = new KeycloakUser(registerRequest.getEmail(), registerRequest.getEmail(), registerRequest.getPassword(), keycloakEnvironment.getRealmName(),keycloakEnvironment.getClientId(), new String[]{KeycloakRealmGroup.ROLES_DEFAULT.name()});
        keycloakUser.setUserId(keycloakService.createUser(keycloakUser));
        keycloakService.joinGroup(keycloakUser);
        keycloakService.resetPassword(keycloakUser);
    }
}
