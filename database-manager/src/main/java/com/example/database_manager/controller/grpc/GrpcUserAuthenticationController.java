package com.example.database_manager.controller.grpc;

import com.example.database_manager.exception.ServiceException;
import com.example.database_manager.service.RegistrationService;
import com.proto.user.UserProtoAuthenticationServiceGrpc;
import com.proto.user.UserProtoConfiguration;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Qualifier;

@GrpcService
public class GrpcUserAuthenticationController extends UserProtoAuthenticationServiceGrpc.UserProtoAuthenticationServiceImplBase {
    protected final RegistrationService<UserProtoConfiguration.UserRegistrationMessage> userRegistrationService;

    public GrpcUserAuthenticationController(@Qualifier("transactionalUserRegistrationProtoServiceImpl") RegistrationService<UserProtoConfiguration.UserRegistrationMessage> userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public void register(UserProtoConfiguration.UserRegistrationMessage request, StreamObserver<UserProtoConfiguration.UserRegistrationMessage> responseObserver) {
        try {
            responseObserver.onNext(userRegistrationService.register(request));
            responseObserver.onCompleted();
        } catch (ServiceException e) {
            responseObserver.onError(e);
        }
    }
}
