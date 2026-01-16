package com.example.database_manager.repository.user.statuses.common;

import java.util.UUID;

public interface CommonUsersStatusesRepository {
    boolean existsByUserUuidAndStatusId(UUID uuid, long statusId);
}
