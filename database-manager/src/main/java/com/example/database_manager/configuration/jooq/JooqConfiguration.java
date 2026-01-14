package com.example.database_manager.configuration.jooq;

import org.jooq.DSLContext;

public abstract class JooqConfiguration {
    protected final DSLContext dslContext;

    public JooqConfiguration(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public DSLContext getDslContext() {
        return dslContext;
    }
}
