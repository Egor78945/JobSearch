package com.example.authentication_service.service.user.registration;

import com.example.authentication_service.exception.FailedOperationException;
import com.example.authentication_service.exception.RegistrationException;
import com.example.authentication_service.model.keycloak.KeycloakUserModel;
import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.model.user.UserRegistrationResponse;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.service.user.common.ReactiveCommonUserService;
import com.proto.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUserGeneralRegistrationService implements RegistrationService<UserModel, Mono<UserRegistrationResponse>> {
    protected final ReactiveCommonUserService reactiveCommonUserService;
    protected final RegistrationService<KeycloakUserModel, Mono<KeycloakUserModel>> keycloakService;
    protected final RegistrationService<UserModel, Mono<UserProtoConfiguration.UserMessage>> userProtoRegistrationService;

    public ReactiveUserGeneralRegistrationService(ReactiveCommonUserService reactiveCommonUserService, RegistrationService<KeycloakUserModel, Mono<KeycloakUserModel>> keycloakService, RegistrationService<UserModel, Mono<UserProtoConfiguration.UserMessage>> userProtoRegistrationService) {
        this.reactiveCommonUserService = reactiveCommonUserService;
        this.keycloakService = keycloakService;
        this.userProtoRegistrationService = userProtoRegistrationService;
    }

    @Override
    public Mono<UserRegistrationResponse> register(UserModel registerRequest) {
        return userProtoRegistrationService.register(registerRequest)
                .flatMap(r -> keycloakService.register(new KeycloakUserModel(registerRequest.getEmail(), registerRequest.getEmail(), r.getUuid(), registerRequest.getPassword())))
                .onErrorResume(e -> reactiveCommonUserService.deleteByEmail(registerRequest.getEmail())
                        .onErrorMap(e2 -> new FailedOperationException("could not to roll back failed registration in the database", e2))
                        .then(Mono.error(new RegistrationException("user registration in keycloak has been rolled back"))))
                .map(k -> new UserRegistrationResponse(k.getUuid(), k.getUserId()));

    }
}
