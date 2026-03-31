package com.example.authentication_service.service.keycloak;

import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RealmsResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReactiveKeycloakResourceManager {
    Mono<RealmsResource> realmsResource();

    Mono<UsersResource> usersResource(String realmName);

    Mono<GroupsResource> groupsResource(String realmName);

    Mono<ClientsResource> clientsResource(String realmName);

    Mono<ClientRepresentation> clientRepresentation(String realmName, String clientId);

    Mono<GroupRepresentation> groupRepresentation(String realmName, String groupName);

    Mono<List<GroupRepresentation>> groupRepresentation(String realmName, String[] groupName);
}
