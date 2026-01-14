package com.example.database_manager.repository.user;

import com.example.database_manager.repository.jooq.JooqRepository;
import com.example.database_manager.repository.user.common.CommonUserRepository;
import com.example.database_manager.util.mapper.UserMapper;
import com.proto.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JooqUserProtoRepository extends JooqRepository implements UserRepository<UserProtoConfiguration.UserMessage> {
    protected final CommonUserRepository commonUserRepository;

    public JooqUserProtoRepository(DSLContext dslContext, CommonUserRepository commonUserRepository) {
        super(dslContext);
        this.commonUserRepository = commonUserRepository;
    }

    @Override
    public UserProtoConfiguration.UserMessage save(UserProtoConfiguration.UserMessage entity) {
        return dslContext
                .insertInto(Tables.USERS)
                .set(Tables.USERS.UUID, commonUserRepository.getUnbusyUuid())
                .set(Tables.USERS.EMAIL, entity.getEmail())
                .set(Tables.USERS.USER_STATUS_ID, entity.getUserStatusId())
                .set(Tables.USERS.REGISTERED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getRegisteredAt()), ZoneId.systemDefault()))
                .returning(Tables.USERS)
                .fetchOne(UserMapper::mapTo);
    }

    @Override
    public UserProtoConfiguration.UserMessage updateByEmail(UserProtoConfiguration.UserMessage entity) {
        return dslContext
                .update(Tables.USERS)
                .set(Tables.USERS.ID, entity.getId())
                .set(Tables.USERS.UUID, commonUserRepository.getUnbusyUuid())
                .set(Tables.USERS.EMAIL, entity.getEmail())
                .set(Tables.USERS.USER_STATUS_ID, entity.getUserStatusId())
                .set(Tables.USERS.REGISTERED_AT, LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getRegisteredAt()), ZoneId.systemDefault()))
                .where(Tables.USERS.EMAIL.eq(entity.getEmail()))
                .returning(Tables.USERS)
                .fetchOne(UserMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.UserMessage> findById(Long id) {
        return dslContext
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .fetchOptional(UserMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.UserMessage> findByUuid(UUID uuid) {
        return dslContext
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.UUID.eq(uuid))
                .fetchOptional(UserMapper::mapTo);
    }

    @Override
    public Optional<UserProtoConfiguration.UserMessage> findByEmail(String email) {
        return dslContext
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.EMAIL.eq(email))
                .fetchOptional(UserMapper::mapTo);
    }
}
