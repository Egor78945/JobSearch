package com.example.database_manager.service.user.common;

import java.util.UUID;

public interface CommonUserService {
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    boolean existsByUuid(UUID uuid);
}
