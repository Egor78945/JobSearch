package com.example.database_manager.configuration.jdbc;

import com.example.database_manager.environment.datasource.DatasourceEnvironment;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcEnvConfigurationImpl implements JdbcConfiguration {
    protected final DatasourceEnvironment datasourceEnvironment;

    public JdbcEnvConfigurationImpl(DatasourceEnvironment datasourceEnvironment) {
        this.datasourceEnvironment = datasourceEnvironment;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(String.format("jdbc:postgresql://%s:%s/%s", datasourceEnvironment.POSTGRES_HOST(), datasourceEnvironment.POSTGRES_PORT(), datasourceEnvironment.POSTGRES_DB()), datasourceEnvironment.POSTGRES_USER(), datasourceEnvironment.POSTGRES_PASSWORD());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
