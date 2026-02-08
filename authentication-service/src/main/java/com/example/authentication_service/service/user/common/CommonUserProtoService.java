package com.example.authentication_service.service.user.common;

import com.example.authentication_service.service.user.grpc.client.UserGrpcClientService;
import com.example.authentication_service.util.mapper.GrpcMapper;
import org.springframework.stereotype.Service;

@Service
public class CommonUserProtoService implements CommonUserService {
    protected final UserGrpcClientService userGrpcClientService;

    public CommonUserProtoService(UserGrpcClientService userGrpcClientService) {
        this.userGrpcClientService = userGrpcClientService;
    }

    @Override
    public void deleteByEmail(String email) {
        userGrpcClientService.deleteByEmail(GrpcMapper.mapTo(email));
    }
}
