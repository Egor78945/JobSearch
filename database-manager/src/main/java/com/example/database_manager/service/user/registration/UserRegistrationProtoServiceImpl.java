package com.example.database_manager.service.user.registration;

import com.example.database_manager.service.RegistrationService;
import com.example.database_manager.service.user.UserService;
import com.example.database_manager.service.user.roles.UsersRolesService;
import com.example.database_manager.util.mapper.UsersRolesMapper;
import com.proto.user.UserProtoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserRegistrationProtoServiceImpl implements RegistrationService<UserProtoConfiguration.UserRegistrationMessage> {
    protected final UserService<UserProtoConfiguration.UserMessage> userService;
    protected final UsersRolesService<UserProtoConfiguration.LongStringMessage> usersRolesService;

    protected UserRegistrationProtoServiceImpl(UserService<UserProtoConfiguration.UserMessage> userService, UsersRolesService<UserProtoConfiguration.LongStringMessage> usersRolesService) {
        this.userService = userService;
        this.usersRolesService = usersRolesService;
    }

    @Override
    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage entity) {
        UserProtoConfiguration.UserMessage registered = userService.save(entity.getUserMessage());

        for(long usersRoleId: entity.getUsersRolesMessageList()) {
            usersRolesService.save(UsersRolesMapper.mapTo(usersRoleId, registered.getUuid()));
        }

        return entity.toBuilder()
                .setUserMessage(registered)
                .build();
    }
}
