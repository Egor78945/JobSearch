package com.example.database_manager.repository.user.statuses;

import com.example.database_manager.repository.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersStatusesRepository<US> extends EntityRepository<US> {
    Optional<US> findByUserUuid(UUID uuid);
    US updateByUserUuidAndStatusId(UUID uuid, long statusId);
}
