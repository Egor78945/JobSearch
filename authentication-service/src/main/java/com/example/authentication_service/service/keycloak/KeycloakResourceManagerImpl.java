package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.exception.NotFoundException;
import com.example.authentication_service.model.keycloak.KeycloakAdminModel;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakResourceManagerImpl implements KeycloakResourceManager {
    private final KeycloakAdminModel keycloakAdmin;

    public KeycloakResourceManagerImpl(KeycloakAdminModel keycloakAdminModel) {
        this.keycloakAdmin = keycloakAdminModel;
    }

    @Override
    public UsersResource usersResource(String realmName) {
        return keycloakAdmin.getKeycloak().realm(realmName).users();
    }

    @Override
    public GroupsResource groupsResource(String realmName) {
        return keycloakAdmin.getKeycloak().realm(realmName).groups();
    }

    @Override
    public ClientsResource clientsResource(String realmName) {
        return keycloakAdmin.getKeycloak().realm(realmName).clients();
    }

    @Override
    public GroupRepresentation groupRepresentation(String realmName, String groupName) {
        return groupsResource(realmName)
                .groups()
                .stream()
                .filter(g -> g.getName().equals(groupName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("group '%s' is not found", groupName)));
    }

    @Override
    public List<GroupRepresentation> groupRepresentation(String realmName, String[] groupName) {
        return Arrays.stream(groupName)
                .map(g -> groupRepresentation(realmName, g))
                .collect(Collectors.toList());
    }
}
