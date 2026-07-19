package com.example.vacancy_manager_service.service.util;

import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class ResourceManager {
    protected final ResourceLoader resourceLoader;

    public ResourceManager(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    InputStream getFile(String name) {
        try {
            return resourceLoader.getResource(String.format("classpath:/%s", name)).getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
