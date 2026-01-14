package com.example.database_manager.configuration.jooq;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfigurationImpl extends JooqConfiguration{
    public JooqConfigurationImpl(DSLContext dslContext) {
        super(dslContext);
    }
}
