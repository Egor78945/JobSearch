package com.example.database_manager.repository.user.roles;

import com.example.database_manager.repository.jooq.JooqRepository;
import com.example.database_manager.util.mapper.UserRoleMapper;
import com.proto.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JooqUsersRolesProtoRepositoryImpl extends JooqRepository implements UsersRolesRepository<UserProtoConfiguration.LongStringMessage> {
    public JooqUsersRolesProtoRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.LongStringMessage save(UserProtoConfiguration.LongStringMessage entity) {
        return dslContext
                .insertInto(Tables.USERS_ROLES)
                .set(Tables.USERS_ROLES.ROLE_ID, entity.getLong())
                .set(Tables.USERS_ROLES.USER_UUID, UUID.fromString(entity.getString()))
                .returning(Tables.USERS_ROLES)
                .fetchOne(UserRoleMapper::mapTo);
    }

    @Override
    public List<UserProtoConfiguration.LongStringMessage> findAllByUserUuid(UUID uuid) {
        return dslContext
                .selectFrom(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.USER_UUID.eq(uuid))
                .fetch(UserRoleMapper::mapTo);
    }
}
