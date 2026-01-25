package com.example.database_manager.service.user.roles;

import com.example.database_manager.service.EntityService;

import java.util.List;
import java.util.UUID;

public interface UsersRolesService<UR> extends EntityService<UR> {
    List<UR> findAllByUserUuid(UUID uuid);
}
