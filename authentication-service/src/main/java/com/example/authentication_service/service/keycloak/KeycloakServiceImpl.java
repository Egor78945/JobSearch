package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.configuration.keycloak.KeycloakHandler;
import com.example.authentication_service.exception.NotFoundException;
import com.example.authentication_service.exception.RequestRejectedException;
import com.example.authentication_service.model.keycloak.KeycloakUser;
import com.example.authentication_service.util.mapper.KeycloakMapper;
import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakServiceImpl implements KeycloakService<KeycloakUser>{
    protected KeycloakHandler keycloakHandler;
    protected KeycloakResourceService keycloakResourceService;

    public KeycloakServiceImpl(KeycloakHandler keycloakHandler, KeycloakResourceService keycloakResourceService) {
        this.keycloakHandler = keycloakHandler;
        this.keycloakResourceService = keycloakResourceService;
    }

    @Override
    public String createUser(KeycloakUser subject) {
        try (Response response = keycloakResourceService.getUsersResource(subject.getRealmName()).create(KeycloakMapper.buildUserRepresentation(subject.getUsername(), subject.getUsername()))) {
            if (response.getStatus() / 100 != 2) {
                throw new RequestRejectedException("user can not be created");
            } else {
                return response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            }
        }
    }
    @Override
    public void resetPassword(KeycloakUser keycloakUser) {
        keycloakResourceService.getUsersResource(keycloakUser.getRealmName())
                .get(keycloakUser.getUserId())
                .resetPassword(KeycloakMapper.buildCredentialRepresentation(keycloakUser.getPassword(), CredentialRepresentation.PASSWORD));
    }

    @Override
    public void joinGroup(KeycloakUser keycloakUser) {
        List<GroupRepresentation> groupRepresentations = keycloakResourceService.getGroupRepresentation(keycloakUser.getRealmName(), keycloakUser.getGroups());
        if (!groupRepresentations.isEmpty()) {
            groupRepresentations
                    .forEach(groupRepresentation -> keycloakResourceService
                            .getUsersResource(keycloakUser.getRealmName())
                            .get(keycloakUser.getUserId())
                            .joinGroup(groupRepresentation.getId()));
        } else {
            throw new NotFoundException("unknown groups");
        }
    }
}
