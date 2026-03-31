package com.example.authentication_service.service.keycloak;

import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RealmsResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;

import java.util.List;

public interface KeycloakResourceManager {
    RealmsResource realmsResource();

    UsersResource usersResource(String realmName);

    GroupsResource groupsResource(String realmName);

    ClientsResource clientsResource(String realmName);

    ClientRepresentation clientRepresentation(String realmName, String clientId);

    GroupRepresentation groupRepresentation(String realmName, String groupName);

    List<GroupRepresentation> groupRepresentation(String realmName, String[] groupName);
}
