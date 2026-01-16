package com.example.database_manager.service.user.statuses;

import com.example.database_manager.exception.NotFoundException;
import com.example.database_manager.repository.user.statuses.UsersStatusesRepository;

import java.util.UUID;

public abstract class UsersStatusesRepositoryService<US> implements UsersStatusesService<US> {
    protected final UsersStatusesRepository<US> usersStatusesRepository;

    public UsersStatusesRepositoryService(UsersStatusesRepository<US> usersStatusesRepository) {
        this.usersStatusesRepository = usersStatusesRepository;
    }

    @Override
    public US save(US entity) {
        return usersStatusesRepository.save(entity);
    }

    @Override
    public US updateByUserUuidAndStatusId(UUID uuid, long statusId) {
        return usersStatusesRepository.updateByUserUuidAndStatusId(uuid, statusId);
    }

    @Override
    public US findByUserUuid(UUID uuid) {
        return usersStatusesRepository.findByUserUuid(uuid).orElseThrow(() -> new NotFoundException(String.format("users statuses not found by user uuid: %s", uuid)));
    }
}
