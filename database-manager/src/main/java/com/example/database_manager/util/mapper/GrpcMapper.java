package com.example.database_manager.util.mapper;

import com.proto.user.UserProtoConfiguration;

public class GrpcMapper {
    public static UserProtoConfiguration.EmptyMessage mapTo() {
        return UserProtoConfiguration.EmptyMessage.newBuilder().build();
    }
}
