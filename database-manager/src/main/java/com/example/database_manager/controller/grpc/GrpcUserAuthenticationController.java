package com.example.database_manager.controller.grpc;

import com.example.database_manager.service.RegistrationService;
import com.proto.user.UserProtoAuthenticationServiceGrpc;
import com.proto.user.UserProtoConfiguration;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcUserAuthenticationController extends UserProtoAuthenticationServiceGrpc.UserProtoAuthenticationServiceImplBase {
    protected final RegistrationService<UserProtoConfiguration.UserRegistrationMessage> userRegistrationService;

    public GrpcUserAuthenticationController(RegistrationService<UserProtoConfiguration.UserRegistrationMessage> userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public void register(UserProtoConfiguration.UserRegistrationMessage request, StreamObserver<UserProtoConfiguration.UserRegistrationMessage> responseObserver) {
        responseObserver.onNext(userRegistrationService.register(request));
        responseObserver.onCompleted();
    }
}
