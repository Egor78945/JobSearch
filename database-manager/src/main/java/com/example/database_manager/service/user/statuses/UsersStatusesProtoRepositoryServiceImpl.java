package com.example.database_manager.service.user.statuses;

import com.example.database_manager.exception.AlreadyExistsException;
import com.example.database_manager.exception.NotFoundException;
import com.example.database_manager.repository.user.statuses.UsersStatusesRepository;
import com.example.database_manager.repository.user.statuses.common.CommonUsersStatusesRepository;
import com.proto.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersStatusesProtoRepositoryServiceImpl extends UsersStatusesRepositoryService<UserProtoConfiguration.LongStringMessage> {
    protected final CommonUsersStatusesRepository commonUsersStatusesRepository;

    public UsersStatusesProtoRepositoryServiceImpl(UsersStatusesRepository<UserProtoConfiguration.LongStringMessage> usersStatusesRepository, CommonUsersStatusesRepository commonUsersStatusesRepository) {
        super(usersStatusesRepository);
        this.commonUsersStatusesRepository = commonUsersStatusesRepository;
    }

    @Override
    public UserProtoConfiguration.LongStringMessage save(UserProtoConfiguration.LongStringMessage entity) {
        if (!commonUsersStatusesRepository.existsByUserUuidAndStatusId(UUID.fromString(entity.getString()), entity.getLong())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("users statuses is already exists: %s", entity));
    }

    @Override
    public UserProtoConfiguration.LongStringMessage updateByUserUuidAndStatusId(UUID uuid, long statusId) {
        if (commonUsersStatusesRepository.existsByUserUuidAndStatusId(uuid, statusId)) {
            return super.updateByUserUuidAndStatusId(uuid, statusId);
        }
        throw new NotFoundException(String.format("users statuses not found by user uuid and status id: %s, %s", uuid, statusId));
    }
}
