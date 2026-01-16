package com.example.database_manager.service.user.roles;

import com.example.database_manager.repository.user.roles.UsersRolesRepository;

import java.util.List;
import java.util.UUID;

public abstract class UsersRolesRepositoryService<UR> implements UsersRolesService<UR> {
    protected final UsersRolesRepository<UR> usersRolesRepository;
    public UsersRolesRepositoryService(UsersRolesRepository<UR> usersRolesRepository) {
        this.usersRolesRepository = usersRolesRepository;
    }

    @Override
    public UR save(UR entity) {
        return usersRolesRepository.save(entity);
    }

    @Override
    public List<UR> findAllByUserUuid(UUID uuid) {
        return usersRolesRepository.findAllByUserUuid(uuid);
    }
}
