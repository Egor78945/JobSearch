package com.example.database_manager.repository.user.roles;

import com.example.database_manager.repository.EntityRepository;

import java.util.List;
import java.util.UUID;

public interface UsersRolesRepository<UR> extends EntityRepository<UR> {
    List<UR> findAllByUserUuid(UUID uuid);
}
