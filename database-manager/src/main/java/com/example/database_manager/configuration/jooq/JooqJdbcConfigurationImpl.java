package com.example.database_manager.configuration.jooq;

import com.example.database_manager.configuration.jdbc.JdbcConfiguration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class JooqJdbcConfigurationImpl implements JooqConfiguration{
    protected final JdbcConfiguration jdbcConfiguration;

    public JooqJdbcConfigurationImpl(JdbcConfiguration jdbcConfiguration) {
        this.jdbcConfiguration = jdbcConfiguration;
    }

    @Override
    public DSLContext dslContext() {
        return DSL.using(jdbcConfiguration.getConnection(), SQLDialect.POSTGRES);
    }
}
