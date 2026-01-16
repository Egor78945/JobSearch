package com.example.database_manager.util.mapper;

import com.proto.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.UserRoleRecord;
import nu.studer.sample.tables.records.UsersRolesRecord;

import java.util.List;

public class UserRoleMapper {
    public static UserProtoConfiguration.LongStringMessage mapTo(UsersRolesRecord record) {
        return UserProtoConfiguration.LongStringMessage
                .newBuilder()
                .setLong(record.getRoleId())
                .setString(record.getUserUuid().toString())
                .build();
    }

    public static List<UserProtoConfiguration.LongStringMessage> mapTo(List<UsersRolesRecord> record) {
        return record.stream()
                .map(UserRoleMapper::mapTo)
                .toList();
    }
}
