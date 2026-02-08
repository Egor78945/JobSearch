package com.example.database_manager.repository.user.common;

import java.util.UUID;

public interface CommonUserRepository {
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    boolean existsByUuid(UUID uuid);
    void deleteByEmail(String email);
    UUID getUnbusyUuid();
}
