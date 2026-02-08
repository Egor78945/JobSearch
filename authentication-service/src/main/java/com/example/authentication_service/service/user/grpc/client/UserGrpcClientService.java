package com.example.authentication_service.service.user.grpc.client;

import com.example.authentication_service.service.grpc.client.GrpcClientService;
import com.proto.user.UserProtoConfiguration;
import com.proto.user.UserProtoServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class UserGrpcClientService extends GrpcClientService<UserProtoServiceGrpc.UserProtoServiceBlockingStub> {
    public UserGrpcClientService(UserProtoServiceGrpc.UserProtoServiceBlockingStub blockingStub) {
        super(blockingStub);
    }

    public void deleteByEmail(UserProtoConfiguration.StringMessage email) {
        blockingStub.deleteByEmail(email);
    }
}
