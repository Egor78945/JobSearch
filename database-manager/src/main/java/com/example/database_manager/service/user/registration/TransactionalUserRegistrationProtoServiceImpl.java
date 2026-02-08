package com.example.database_manager.service.user.registration;

import com.example.database_manager.service.user.UserService;
import com.example.database_manager.service.user.roles.UsersRolesService;
import com.proto.user.UserProtoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class TransactionalUserRegistrationProtoServiceImpl extends UserRegistrationProtoServiceImpl{
    public TransactionalUserRegistrationProtoServiceImpl(UserService<UserProtoConfiguration.UserMessage> userService, UsersRolesService<UserProtoConfiguration.LongStringMessage> usersRolesService) {
        super(userService, usersRolesService);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage entity) {
        return super.register(entity);
    }
}
