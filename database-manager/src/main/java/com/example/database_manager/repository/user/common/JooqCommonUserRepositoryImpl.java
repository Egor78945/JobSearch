package com.example.database_manager.repository.user.common;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqCommonUserRepositoryImpl extends JooqCommonUserRepository{
    public JooqCommonUserRepositoryImpl(DSLContext dslContext) {
        super(dslContext);
    }
}
