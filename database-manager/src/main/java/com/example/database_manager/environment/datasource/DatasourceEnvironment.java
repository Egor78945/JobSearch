package com.example.database_manager.environment.datasource;

import org.springframework.stereotype.Component;

public class DatasourceEnvironment {
    public String POSTGRES_HOST() {
        return System.getenv("POSTGRES_HOST");
    }

    public String POSTGRES_PORT() {
        return System.getenv("POSTGRES_PORT");
    }

    public String POSTGRES_DB() {
        return System.getenv("POSTGRES_DB");
    }

    public String POSTGRES_USER() {
        return System.getenv("POSTGRES_USER");
    }

    public String POSTGRES_PASSWORD() {
        return System.getenv("POSTGRES_PASSWORD");
    }
}
