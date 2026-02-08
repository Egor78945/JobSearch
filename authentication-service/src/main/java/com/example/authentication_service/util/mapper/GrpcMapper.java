package com.example.authentication_service.util.mapper;

import com.proto.user.UserProtoConfiguration;

public class GrpcMapper {
    public static UserProtoConfiguration.StringMessage mapTo(String string) {
        return UserProtoConfiguration.StringMessage.newBuilder()
                .setString(string)
                .build();
    }
}
