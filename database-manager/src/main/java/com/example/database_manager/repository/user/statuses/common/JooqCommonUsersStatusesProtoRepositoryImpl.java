package com.example.database_manager.repository.user.statuses.common;

import com.example.database_manager.repository.jooq.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JooqCommonUsersStatusesProtoRepositoryImpl extends JooqRepository implements CommonUsersStatusesRepository {
    public JooqCommonUsersStatusesProtoRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public boolean existsByUserUuidAndStatusId(UUID uuid, long statusId) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectFrom(Tables.USERS_STATUSES)
                                .where(Tables.USERS_STATUSES.USER_UUID.eq(uuid)
                                        .and(Tables.USERS_STATUSES.STATUS_ID.eq(statusId)))
                );
    }
}
