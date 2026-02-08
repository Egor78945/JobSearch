package com.example.database_manager.service.user.roles.common;

import java.util.UUID;

public interface CommonUsersRolesService {
    void deleteAllByUserEmail(String email);
    boolean existsByUserUuidAndRoleId(UUID uuid, long roleId);
}
