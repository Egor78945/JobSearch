package com.example.database_manager.service.user;

import com.example.database_manager.exception.AlreadyExistsException;
import com.example.database_manager.exception.NotFoundException;
import com.example.database_manager.repository.user.UserRepository;
import com.example.database_manager.repository.user.common.CommonUserRepository;
import com.proto.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class UserProtoRepositoryServiceImpl extends UserRepositoryService<UserProtoConfiguration.UserMessage> {
    protected final CommonUserRepository commonUserRepository;

    public UserProtoRepositoryServiceImpl(UserRepository<UserProtoConfiguration.UserMessage> userRepository, CommonUserRepository commonUserRepository) {
        super(userRepository);
        this.commonUserRepository = commonUserRepository;
    }

    @Override
    public UserProtoConfiguration.UserMessage save(UserProtoConfiguration.UserMessage entity) {
        if (!commonUserRepository.existsByEmail(entity.getEmail())) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("user is already exists by email: %s", entity.getEmail()));
    }

    @Override
    public UserProtoConfiguration.UserMessage updateByEmail(UserProtoConfiguration.UserMessage user) {
        if (commonUserRepository.existsByEmail(user.getEmail())) {
            return super.updateByEmail(user);
        }
        throw new NotFoundException(String.format("user not found by email: %s", user.getEmail()));
    }
}
