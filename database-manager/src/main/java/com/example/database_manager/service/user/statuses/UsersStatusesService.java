package com.example.database_manager.service.user.statuses;

import com.example.database_manager.service.EntityService;

import java.util.UUID;

public interface UsersStatusesService<US> extends EntityService<US> {
    US updateByUserUuidAndStatusId(UUID uuid, long statusId);
    US findByUserUuid(UUID uuid);
}
