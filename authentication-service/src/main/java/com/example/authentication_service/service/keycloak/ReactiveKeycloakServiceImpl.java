package com.example.authentication_service.service.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.exception.RequestRejectedException;
import com.example.authentication_service.model.keycloak.KeycloakUserModel;
import com.example.authentication_service.util.mapper.KeycloakMapper;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ReactiveKeycloakServiceImpl implements ReactiveKeycloakService<KeycloakUserModel> {
    protected final KeycloakEnvironment keycloakEnvironment;
    protected final ReactiveKeycloakResourceManager keycloakResourceManager;

    public ReactiveKeycloakServiceImpl(KeycloakEnvironment keycloakEnvironment, ReactiveKeycloakResourceManager keycloakResourceManager) {
        this.keycloakEnvironment = keycloakEnvironment;
        this.keycloakResourceManager = keycloakResourceManager;
    }

    @Override
    public Mono<String> createUser(KeycloakUserModel subject) {
        return keycloakResourceManager.usersResource(subject.getRealmName())
                .flatMap(u -> Mono.fromCallable(() -> u.create(KeycloakMapper.buildUserRepresentation(subject.getUsername(), subject.getEmail(), subject.getUuid())))
                        .subscribeOn(Schedulers.boundedElastic()))
                .filter(r -> r.getStatus() / 100 == 2)
                .map(r -> r.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1"))
                .switchIfEmpty(Mono.error(new RequestRejectedException("user can not be registered")));
    }

    @Override
    public Mono<Void> resetPassword(KeycloakUserModel keycloakUserModel) {
        return keycloakResourceManager.usersResource(keycloakUserModel.getRealmName())
                .flatMap(r -> Mono.fromRunnable(() -> {
                            UserResource ur = r.get(keycloakUserModel.getUserId());
                            ur.resetPassword(KeycloakMapper.buildCredentialRepresentation(keycloakUserModel.getPassword(), CredentialRepresentation.PASSWORD));
                        })
                        .onErrorMap(e -> new RequestRejectedException("could not reset user password", e))
                        .subscribeOn(Schedulers.boundedElastic()))
                .then();
    }

    @Override
    public Mono<Void> joinGroup(KeycloakUserModel keycloakUserModel) {
        return keycloakResourceManager.groupRepresentation(keycloakUserModel.getRealmName(), keycloakUserModel.getGroups())
                .flatMap(l -> Flux.fromIterable(l)
                        .flatMap(g -> joinGroup(keycloakUserModel, g))
                    .then())
                .then();
    }

    private Mono<Void> joinGroup(KeycloakUserModel keycloakUserModel, GroupRepresentation groupRepresentation) {
        return keycloakResourceManager.usersResource(keycloakUserModel.getRealmName())
                .flatMap(r -> Mono.fromRunnable(() -> {
                            UserResource ur = r.get(keycloakUserModel.getUserId());
                            ur.joinGroup(groupRepresentation.getId());
                        })
                        .onErrorMap(e -> new RequestRejectedException("failed to join group", e))
                        .subscribeOn(Schedulers.boundedElastic()))
                .then();

    }
}
