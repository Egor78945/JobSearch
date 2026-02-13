package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.exception.NotFoundException;
import com.example.authentication_service.exception.RequestRejectedException;
import com.example.authentication_service.model.keycloak.KeycloakUserModel;
import com.example.authentication_service.util.mapper.KeycloakMapper;
import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakServiceImpl implements KeycloakService<KeycloakUserModel>{
    protected KeycloakResourceManager keycloakResourceManager;

    public KeycloakServiceImpl(KeycloakResourceManager keycloakResourceManager) {
        this.keycloakResourceManager = keycloakResourceManager;
    }

    @Override
    public String createUser(KeycloakUserModel subject) {
        try (Response response = keycloakResourceManager.usersResource(subject.getRealmName()).create(KeycloakMapper.buildUserRepresentation(subject.getUsername(), subject.getEmail()))) {
            if (response.getStatus() / 100 != 2) {
                throw new RequestRejectedException("user can not be created");
            } else {
                return response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            }
        }
    }

    @Override
    public void resetPassword(KeycloakUserModel keycloakUserModel) {
        keycloakResourceManager.usersResource(keycloakUserModel.getRealmName())
                .get(keycloakUserModel.getUserId())
                .resetPassword(KeycloakMapper.buildCredentialRepresentation(keycloakUserModel.getPassword(), CredentialRepresentation.PASSWORD));
    }

    @Override
    public void joinGroup(KeycloakUserModel keycloakUserModel) {
        List<GroupRepresentation> groupRepresentations = keycloakResourceManager.groupRepresentation(keycloakUserModel.getRealmName(), keycloakUserModel.getGroups());
        if (!groupRepresentations.isEmpty()) {
            groupRepresentations
                    .forEach(groupRepresentation -> keycloakResourceManager
                            .usersResource(keycloakUserModel.getRealmName())
                            .get(keycloakUserModel.getUserId())
                            .joinGroup(groupRepresentation.getId()));
        } else {
            throw new NotFoundException("unknown groups");
        }
    }
}
