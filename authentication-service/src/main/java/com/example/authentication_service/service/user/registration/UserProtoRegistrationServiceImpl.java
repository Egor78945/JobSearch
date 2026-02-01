package com.example.authentication_service.service.user.registration;

import com.example.authentication_service.enumeration.UserRole;
import com.example.authentication_service.enumeration.UserStatus;
import com.example.authentication_service.model.user.security.UserAuthenticationModel;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.service.user.grpc.client.UserAuthenticationGrpcClientService;
import com.example.authentication_service.util.mapper.UserMapper;
import com.proto.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserProtoRegistrationServiceImpl implements RegistrationService<UserAuthenticationModel> {
    protected final UserAuthenticationGrpcClientService grpcClientService;

    public UserProtoRegistrationServiceImpl(UserAuthenticationGrpcClientService userAuthenticationGrpcClientService) {
        this.grpcClientService = userAuthenticationGrpcClientService;
    }


    @Override
    public void register(UserAuthenticationModel registerRequest) {
        UserProtoConfiguration.UserMessage userMessage = UserMapper.mapTo(0, "", registerRequest.getEmail(), UserStatus.STATUS_ACTIVE.getId(), Instant.now().toEpochMilli());
        UserProtoConfiguration.UserRegistrationMessage userRegistrationMessage = UserMapper.mapTo(userMessage, List.of(UserRole.ROLE_USER.getId()));
        grpcClientService.register(userRegistrationMessage);
    }
}
