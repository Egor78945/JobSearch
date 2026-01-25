package com.example.database_manager.repository.jooq;

import org.jooq.DSLContext;

public abstract class JooqRepository {
    protected final DSLContext dslContext;

    public JooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
