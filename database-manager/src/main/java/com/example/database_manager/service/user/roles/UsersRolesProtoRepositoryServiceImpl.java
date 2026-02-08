package com.example.database_manager.service.user.roles;

import com.example.database_manager.exception.AlreadyExistsException;
import com.example.database_manager.repository.user.roles.UsersRolesRepository;
import com.example.database_manager.service.user.roles.common.CommonUsersRolesService;
import com.proto.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersRolesProtoRepositoryServiceImpl extends UsersRolesRepositoryService<UserProtoConfiguration.LongStringMessage> {
    protected final CommonUsersRolesService commonUsersRolesRepository;

    public UsersRolesProtoRepositoryServiceImpl(UsersRolesRepository<UserProtoConfiguration.LongStringMessage> usersRolesRepository, CommonUsersRolesService commonUsersRolesRepository) {
        super(usersRolesRepository);
        this.commonUsersRolesRepository = commonUsersRolesRepository;
    }

    @Override
    public UserProtoConfiguration.LongStringMessage save(UserProtoConfiguration.LongStringMessage entity) {
        if (!commonUsersRolesRepository.existsByUserUuidAndRoleId(UUID.fromString(entity.getString()), entity.getLong())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("users roles is already exists: %s", entity));
    }
}
