package com.example.database_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class DatabaseManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseManagerApplication.class, args);
    }

}
