package com.example.database_manager.configuration.jdbc;

import java.sql.Connection;

public interface JdbcConfiguration {
    Connection getConnection();
}
