package com.example.database_manager.repository.user.roles.common;

import java.util.UUID;

public interface CommonUsersRolesRepository {
    boolean existsByUserUuidAndRoleId(UUID uuid, long roleId);
}
