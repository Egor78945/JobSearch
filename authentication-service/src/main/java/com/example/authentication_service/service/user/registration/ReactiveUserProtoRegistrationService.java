package com.example.authentication_service.service.user.registration;

import com.example.authentication_service.enumeration.UserRole;
import com.example.authentication_service.enumeration.UserStatus;
import com.example.authentication_service.exception.RegistrationException;
import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.service.user.grpc.client.UserAuthenticationGrpcClientService;
import com.example.authentication_service.util.mapper.UserMapper;
import com.proto.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.List;

@Service
public class ReactiveUserProtoRegistrationService implements RegistrationService<UserModel, Mono<UserProtoConfiguration.UserMessage>> {
    protected final UserAuthenticationGrpcClientService grpcClientService;

    public ReactiveUserProtoRegistrationService(UserAuthenticationGrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @Override
    public Mono<UserProtoConfiguration.UserMessage> register(UserModel registerRequest) {
        UserProtoConfiguration.UserMessage userMessage = UserMapper.mapTo(registerRequest.getEmail(), UserStatus.STATUS_ACTIVE.getId(), Instant.now().toEpochMilli());
        UserProtoConfiguration.UserRegistrationMessage userRegistrationMessage = UserMapper.mapTo(userMessage, List.of(UserRole.ROLE_USER.getId()));

        return Mono.fromCallable(() -> grpcClientService.register(userRegistrationMessage).getUserMessage())
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorMap(e -> new RegistrationException("failed to save the user in the database", e));
    }
}
