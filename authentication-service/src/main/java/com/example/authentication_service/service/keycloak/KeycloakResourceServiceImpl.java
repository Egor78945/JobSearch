package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.configuration.keycloak.KeycloakHandler;
import com.example.authentication_service.exception.NotFoundException;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakResourceServiceImpl implements KeycloakResourceService {
    private final KeycloakHandler keycloakHandler;

    public KeycloakResourceServiceImpl(KeycloakHandler keycloakHandler) {
        this.keycloakHandler = keycloakHandler;
    }

    @Override
    public UsersResource getUsersResource(String realmName) {
        return keycloakHandler.getKeycloak().realm(realmName).users();
    }

    @Override
    public RolesResource getRolesResource(String realmName) {
        return keycloakHandler.getKeycloak().realm(realmName).roles();
    }

    @Override
    public GroupsResource getGroupsResource(String realmName) {
        return keycloakHandler.getKeycloak().realm(realmName).groups();
    }

    @Override
    public ClientsResource getClientsResource(String realmName) {
        return keycloakHandler.getKeycloak().realm(realmName).clients();
    }

    @Override
    public GroupRepresentation getGroupRepresentation(String realmName, String groupName) {
        return getGroupsResource(realmName)
                .groups()
                .stream()
                .filter(g -> g.getName().equals(groupName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("group '%s' is not found", groupName)));
    }

    @Override
    public List<GroupRepresentation> getGroupRepresentation(String realmName, String[] groupName) {
        return Arrays.stream(groupName)
                .map(g -> getGroupRepresentation(realmName, g))
                .collect(Collectors.toList());
    }
}
