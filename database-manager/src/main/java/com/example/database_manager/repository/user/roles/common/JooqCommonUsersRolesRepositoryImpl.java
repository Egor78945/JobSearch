package com.example.database_manager.repository.user.roles.common;

import com.example.database_manager.repository.jooq.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JooqCommonUsersRolesRepositoryImpl extends JooqRepository implements CommonUsersRolesRepository {
    public JooqCommonUsersRolesRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public boolean existsByUserUuidAndRoleId(UUID uuid, long roleId) {
        return dslContext
                .fetchExists(
                        dslContext
                                .selectFrom(Tables.USERS_ROLES)
                                .where(Tables.USERS_ROLES.USER_UUID.eq(uuid)
                                        .and(Tables.USERS_ROLES.ROLE_ID.eq(roleId)))
                );
    }

    @Override
    public void deleteAllByUserEmail(String email) {
        dslContext
                .deleteFrom(Tables.USERS_ROLES)
                .where(Tables.USERS_ROLES.USER_UUID.in(
                        dslContext
                                .select(Tables.USERS.UUID)
                                .from(Tables.USERS)
                                .where(Tables.USERS.EMAIL.eq(email))
                ))
                .execute();
    }
}
