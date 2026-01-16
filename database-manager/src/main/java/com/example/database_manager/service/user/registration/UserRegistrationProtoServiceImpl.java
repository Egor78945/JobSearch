package com.example.database_manager.service.user.registration;

import com.example.database_manager.service.RegistrationService;
import com.example.database_manager.service.user.UserService;
import com.example.database_manager.service.user.roles.UsersRolesService;
import com.proto.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
        List<UserProtoConfiguration.LongStringMessage> savedRolesList = new ArrayList<>();
        for(UserProtoConfiguration.LongStringMessage userRoleMessage: entity.getUsersRolesMessageList()) {
            savedRolesList.add(usersRolesService.save(userRoleMessage));
        }

        return entity.toBuilder()
                .setUserMessage(registered)
                .addAllUsersRolesMessage(savedRolesList)
                .build();
    }
}
