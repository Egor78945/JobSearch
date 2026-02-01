package com.example.authentication_service.service.user.grpc.client;

import com.example.authentication_service.service.grpc.client.GrpcClientService;
import com.proto.user.UserProtoAuthenticationServiceGrpc;
import com.proto.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationGrpcClientService extends GrpcClientService<UserProtoAuthenticationServiceGrpc.UserProtoAuthenticationServiceBlockingStub> {
    public UserAuthenticationGrpcClientService(UserProtoAuthenticationServiceGrpc.UserProtoAuthenticationServiceBlockingStub blockingStub) {
        super(blockingStub);
    }

    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage userRegistrationMessage) {
        return blockingStub.register(userRegistrationMessage);
    }
}

