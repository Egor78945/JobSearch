package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.exception.RequestRejectedException;
import com.example.authentication_service.model.keycloak.KeycloakAdminModel;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Component
public class ReactiveKeycloakResourceManagerImpl implements ReactiveKeycloakResourceManager {
    protected final KeycloakAdminModel keycloakAdmin;
    protected final KeycloakEnvironment keycloakEnvironment;

    public ReactiveKeycloakResourceManagerImpl(KeycloakAdminModel keycloakAdminModel, KeycloakEnvironment keycloakEnvironment) {
        this.keycloakAdmin = keycloakAdminModel;
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public Mono<RealmsResource> realmsResource() {
        return Mono.fromCallable(() -> keycloakAdmin.getKeycloak().realms())
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorMap(e -> new RequestRejectedException("failed to get keycloak realms"));
    }

    @Override
    public Mono<UsersResource> usersResource(String realmName) {
        return realmResource(realmName).map(RealmResource::users);
    }

    @Override
    public Mono<GroupsResource> groupsResource(String realmName) {
        return realmResource(realmName).map(RealmResource::groups);
    }

    @Override
    public Mono<ClientsResource> clientsResource(String realmName) {
        return realmResource(realmName).map(RealmResource::clients);
    }

    @Override
    public Mono<ClientRepresentation> clientRepresentation(String realmName, String clientId) {
        return realmResource(realmName).map(RealmResource::clients).map(c -> c.findByClientId(clientId).getFirst());
    }

    @Override
    public Mono<GroupRepresentation> groupRepresentation(String realmName, String groupName) {
        return realmResource(realmName).map(RealmResource::groups).map(g -> g.group(groupName).toRepresentation());
    }

    @Override
    public Mono<List<GroupRepresentation>> groupRepresentation(String realmName, String[] groupName) {
        return groupsResource(realmName)
                .flatMap(gr -> Flux.fromArray(groupName)
                        .flatMap(gn -> groupRepresentation(realmName, gn))
                        .collectList()
                        .subscribeOn(Schedulers.boundedElastic())
                );
    }

    private Mono<RealmResource> realmResource(String realmName) {
        return Mono.fromCallable(() -> keycloakAdmin.getKeycloak().realm(realmName))
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorMap(e -> new RequestRejectedException("failed to get keycloak realms"));
    }
}
