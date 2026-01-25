package com.example.database_manager.util.mapper;

import com.proto.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.UsersRecord;

import java.time.ZoneId;

public class UsersMapper {
    public static UserProtoConfiguration.UserMessage mapTo(UsersRecord user) {
        return UserProtoConfiguration.UserMessage
                .newBuilder()
                .setId(user.getId())
                .setUuid(user.getUuid().toString())
                .setEmail(user.getEmail())
                .setUserStatusId(user.getUserStatusId())
                .setRegisteredAt(user.getRegisteredAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .build();
    }
}
