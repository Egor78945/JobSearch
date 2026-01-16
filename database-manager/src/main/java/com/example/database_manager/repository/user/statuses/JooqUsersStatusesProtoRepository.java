package com.example.database_manager.repository.user.statuses;

import com.example.database_manager.repository.jooq.JooqRepository;
import com.example.database_manager.util.mapper.UserStatusMapper;
import com.proto.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JooqUsersStatusesProtoRepository extends JooqRepository implements UsersStatusesRepository<UserProtoConfiguration.LongStringMessage> {
    public JooqUsersStatusesProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.LongStringMessage save(UserProtoConfiguration.LongStringMessage entity) {
        return dslContext
                .insertInto(Tables.USERS_STATUSES)
                .set(Tables.USERS_STATUSES.STATUS_ID, entity.getLong())
                .set(Tables.USERS_STATUSES.USER_UUID, UUID.fromString(entity.getString()))
                .returning(Tables.USERS_STATUSES)
                .fetchOne(UserStatusMapper::mapTo);
    }

    @Override
    public UserProtoConfiguration.LongStringMessage updateByUserUuidAndStatusId(UUID uuid, long statusId) {
        return dslContext
                .update(Tables.USERS_STATUSES)
                .set(Tables.USERS_STATUSES.USER_UUID, uuid)
                .set(Tables.USERS_STATUSES.STATUS_ID, statusId)
                .returning(Tables.USERS_STATUSES)
                .fetchOne(UserStatusMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.LongStringMessage> findByUserUuid(UUID uuid) {
        return dslContext
                .selectFrom(Tables.USERS_STATUSES)
                .where(Tables.USERS_STATUSES.USER_UUID.eq(uuid))
                .fetchOptional(UserStatusMapper::mapTo);
    }
}
