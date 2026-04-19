package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.exception.NotFoundException;
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
                .onErrorMap(e -> new RequestRejectedException("failed to get keycloak realms: " + e.getMessage()));
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
        return clientsResource(realmName).flatMap(c -> Mono.fromCallable(() -> c.findByClientId(clientId).getFirst())
                .subscribeOn(Schedulers.boundedElastic()));
    }

    @Override
    public Mono<GroupRepresentation> groupRepresentation(String realmName, String groupName) {
        return groupsResource(realmName)
                .flatMap(g -> Flux.fromIterable(g.groups())
                        .filter(group -> group.getName().equals(groupName))
                        .collectList()
                        .subscribeOn(Schedulers.boundedElastic()))
                .map(List::getFirst)
                .switchIfEmpty(Mono.error(new NotFoundException("unknown group: " + groupName)));

    }

    @Override
    public Mono<List<GroupRepresentation>> groupRepresentation(String realmName, String[] groupName) {
        return Flux.fromArray(groupName)
                .flatMap(gn -> groupRepresentation(realmName, gn))
                .subscribeOn(Schedulers.boundedElastic())
                .collectList();
    }

    private Mono<RealmResource> realmResource(String realmName) {
        return realmsResource()
                .flatMap(r -> Mono.fromCallable(() -> r.realm(realmName))
                        .subscribeOn(Schedulers.boundedElastic()))
                .onErrorMap(e -> new RequestRejectedException("failed to get realm resource", e));
    }
}