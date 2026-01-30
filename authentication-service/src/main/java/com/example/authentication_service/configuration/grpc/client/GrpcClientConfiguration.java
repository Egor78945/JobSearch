package com.example.authentication_service.configuration.grpc.client;

import com.proto.user.UserProtoAuthenticationServiceGrpc;
import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfiguration {
    @GrpcClient("database-manager-grpc-service")
    private Channel channel;

    @Bean
    public UserProtoAuthenticationServiceGrpc.UserProtoAuthenticationServiceBlockingStub userProtoAuthenticationServiceBlockingStub(){
        return UserProtoAuthenticationServiceGrpc.newBlockingStub(channel);
    }
}
