package com.example.vacancy_manager_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VacancyManagerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacancyManagerServiceApplication.class, args);
    }

}
