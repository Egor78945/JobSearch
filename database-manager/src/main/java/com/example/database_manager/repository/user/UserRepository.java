package com.example.database_manager.repository.user;

import com.example.database_manager.repository.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository<U> extends EntityRepository<U> {
    U updateByEmail(U user);
    Optional<U> findById(Long id);
    Optional<U> findByEmail(String email);
    Optional<U> findByUuid(UUID uuid);
}
