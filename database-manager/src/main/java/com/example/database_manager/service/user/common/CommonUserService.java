package com.example.database_manager.service.user.common;

import java.util.UUID;

public interface CommonUserService {
    void deleteByEmail(String email);
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    boolean existsByUuid(UUID uuid);
}
