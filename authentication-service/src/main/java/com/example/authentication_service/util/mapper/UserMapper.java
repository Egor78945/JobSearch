package com.example.authentication_service.util.mapper;

import com.proto.user.UserProtoConfiguration;

import java.util.List;

public class UserMapper {
    public static UserProtoConfiguration.UserMessage mapTo(long id, String uuid, String email, long userStatusId, long registeredAt) {
        return UserProtoConfiguration.UserMessage.newBuilder()
                .setId(id)
                .setUuid(uuid)
                .setEmail(email)
                .setUserStatusId(userStatusId)
                .setRegisteredAt(registeredAt)
                .build();
    }

    public static UserProtoConfiguration.UserMessage mapTo(String email, long userStatusId, long registeredAt) {
        return UserProtoConfiguration.UserMessage.newBuilder()
                .setEmail(email)
                .setUserStatusId(userStatusId)
                .setRegisteredAt(registeredAt)
                .build();
    }

    public static UserProtoConfiguration.UserRegistrationMessage mapTo(UserProtoConfiguration.UserMessage userMessage, List<Long> userRoles) {
        return UserProtoConfiguration.UserRegistrationMessage.newBuilder()
                .setUserMessage(userMessage)
                .addAllUsersRolesMessage(userRoles)
                .build();
    }
}
