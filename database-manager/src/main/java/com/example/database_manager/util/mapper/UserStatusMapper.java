package com.example.database_manager.util.mapper;

import com.proto.user.UserProtoConfiguration;
import nu.studer.sample.tables.records.UsersStatusesRecord;

public class UserStatusMapper {
    public static UserProtoConfiguration.LongStringMessage mapTo(UsersStatusesRecord userStatusesRecord) {
        return UserProtoConfiguration.LongStringMessage
                .newBuilder()
                .setLong(userStatusesRecord.getStatusId())
                .setString(userStatusesRecord.getUserUuid().toString())
                .build();
    }
}
