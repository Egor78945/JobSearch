package com.example.database_manager.repository.user.common;

import com.example.database_manager.repository.jooq.JooqRepository;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqCommonUserRepository extends JooqRepository implements CommonUserRepository {
    public JooqCommonUserRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }
}
