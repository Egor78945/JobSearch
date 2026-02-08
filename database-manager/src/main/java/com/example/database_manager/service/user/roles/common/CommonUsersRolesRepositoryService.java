package com.example.database_manager.service.user.roles.common;

import com.example.database_manager.repository.user.roles.common.CommonUsersRolesRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonUsersRolesRepositoryService implements CommonUsersRolesService{
    protected final CommonUsersRolesRepository usersRolesRepository;

    public CommonUsersRolesRepositoryService(CommonUsersRolesRepository usersRolesRepository) {
        this.usersRolesRepository = usersRolesRepository;
    }

    @Override
    public void deleteAllByUserEmail(String email) {
        usersRolesRepository.deleteAllByUserEmail(email);
    }

    @Override
    public boolean existsByUserUuidAndRoleId(UUID uuid, long roleId) {
        return usersRolesRepository.existsByUserUuidAndRoleId(uuid, roleId);
    }
}
