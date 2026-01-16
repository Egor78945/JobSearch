package com.example.database_manager.service.user;

import com.example.database_manager.exception.NotFoundException;
import com.example.database_manager.repository.user.UserRepository;

import java.util.UUID;

public abstract class UserRepositoryService<U> implements UserService<U> {
    protected final UserRepository<U> userRepository;

    public UserRepositoryService(UserRepository<U> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public U save(U entity) {
        return userRepository.save(entity);
    }

    @Override
    public U updateByEmail(U user) {
        return userRepository.updateByEmail(user);
    }

    @Override
    public U findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("user not found by id: %s", id)));
    }

    @Override
    public U findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(String.format("user not found by email: %s", email)));
    }

    @Override
    public U findByUuid(UUID uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(String.format("user not found by uuid: %s", uuid)));
    }
}
