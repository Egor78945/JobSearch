package com.example.database_manager.repository.user.common;

import com.example.database_manager.exception.FailedOperationException;
import com.example.database_manager.repository.jooq.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JooqCommonUserRepositoryImpl extends JooqRepository implements CommonUserRepository {
    public JooqCommonUserRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public boolean existsById(Long id) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.USERS)
                                .where(Tables.USERS.ID.eq(id))
                );
    }

    @Override
    public boolean existsByEmail(String email) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.USERS)
                                .where(Tables.USERS.EMAIL.eq(email))
                );
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectOne()
                                .from(Tables.USERS)
                                .where(Tables.USERS.UUID.eq(uuid))
                );
    }

    @Override
    public void deleteByEmail(String email) {
        dslContext
                .deleteFrom(Tables.USERS)
                .where(Tables.USERS.EMAIL.eq(email))
                .execute();
    }

    @Override
    public UUID getUnbusyUuid() {
        UUID uuid = UUID.randomUUID();
        for (int i = 0; i < 10; i++) {
            if(existsByUuid(uuid)) {
                uuid = UUID.randomUUID();
            } else {
                return uuid;
            }
        }

        if (existsByUuid(uuid)) {
            throw new FailedOperationException("failed to generate unbusy uuid");
        }
        return uuid;
    }
}
