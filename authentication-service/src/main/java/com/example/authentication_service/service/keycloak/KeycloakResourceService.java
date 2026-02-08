package com.example.authentication_service.service.keycloak;

import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;

import java.util.List;

public interface KeycloakResourceService {
    UsersResource getUsersResource(String realmName);

    RolesResource getRolesResource(String realmName);

    GroupsResource getGroupsResource(String realmName);

    ClientsResource getClientsResource(String realmName);

    GroupRepresentation getGroupRepresentation(String realmName, String groupName);

    List<GroupRepresentation> getGroupRepresentation(String realmName, String[] groupName);
}
