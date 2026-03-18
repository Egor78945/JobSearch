package com.example.database_manager.controller.grpc;

import com.example.database_manager.service.user.UserService;
import com.example.database_manager.service.user.common.CommonUserService;
import com.example.database_manager.util.mapper.GrpcMapper;
import com.proto.user.UserProtoConfiguration;
import com.proto.user.UserProtoServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class GrpcUserController extends UserProtoServiceGrpc.UserProtoServiceImplBase {
    protected final UserService<UserProtoConfiguration.UserMessage> userService;
    protected final CommonUserService commonUserService;

    public GrpcUserController(UserService<UserProtoConfiguration.UserMessage> userService, CommonUserService commonUserService) {
        this.userService = userService;
        this.commonUserService = commonUserService;
    }

    @Override
    public void findById(UserProtoConfiguration.LongMessage request, StreamObserver<UserProtoConfiguration.UserMessage> responseObserver) {
            responseObserver.onNext(userService.findById(request.getLong()));
            responseObserver.onCompleted();
    }

    @Override
    public void findByUuid(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.UserMessage> responseObserver) {
            responseObserver.onNext(userService.findByUuid(UUID.fromString(request.getString())));
            responseObserver.onCompleted();
    }

    @Override
    public void findByEmail(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.UserMessage> responseObserver) {
            responseObserver.onNext(userService.findByEmail(request.getString()));
            responseObserver.onCompleted();
    }

    @Override
    public void deleteByEmail(UserProtoConfiguration.StringMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
            commonUserService.deleteByEmail(request.getString());
            responseObserver.onNext(GrpcMapper.mapTo());
            responseObserver.onCompleted();
    }
}
