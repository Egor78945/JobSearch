package com.example.database_manager.service.user;

import com.example.database_manager.service.EntityService;

import java.util.UUID;

public interface UserService<U> extends EntityService<U> {
    U updateByEmail(U user);
    U findById(Long id);
    U findByEmail(String email);
    U findByUuid(UUID uuid);
}
