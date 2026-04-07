package com.example.authentication_service.service.user.registration;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.enumeration.KeycloakRealmGroup;
import com.example.authentication_service.model.keycloak.KeycloakUserModel;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.service.keycloak.ReactiveKeycloakService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ReactiveUserKeycloakRegistrationService implements RegistrationService<KeycloakUserModel, Mono<KeycloakUserModel>> {
    protected final KeycloakEnvironment keycloakEnvironment;
    protected final ReactiveKeycloakService<KeycloakUserModel> keycloakService;

    public ReactiveUserKeycloakRegistrationService(KeycloakEnvironment keycloakEnvironment, ReactiveKeycloakService<KeycloakUserModel> keycloakService) {
        this.keycloakEnvironment = keycloakEnvironment;
        this.keycloakService = keycloakService;
    }

    @Override
    public Mono<KeycloakUserModel> register(KeycloakUserModel registerRequest) {
        KeycloakUserModel registerModel = new KeycloakUserModel(registerRequest.getEmail(), registerRequest.getEmail(), registerRequest.getUuid(), registerRequest.getPassword(), keycloakEnvironment.getAuthenticationRealmName(),keycloakEnvironment.getAuthenticationClientId(), new String[]{KeycloakRealmGroup.ROLES_DEFAULT.name()});
        return keycloakService.createUser(registerModel)
                .map(id -> {
                    registerModel.setUserId(id);
                    return registerModel;
                })
                .flatMapMany(r -> Flux.concat(keycloakService.resetPassword(r), keycloakService.joinGroup(r)))
                .then(Mono.just(registerModel));
    }
}
