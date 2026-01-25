package com.example.database_manager.configuration.jooq;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public interface JooqConfiguration {
    DSLContext dslContext();
}
