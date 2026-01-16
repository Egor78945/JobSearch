package com.example.database_manager.service;

public interface EntityService<E> {
    E save(E entity);
}
