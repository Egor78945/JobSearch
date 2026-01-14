package com.example.database_manager.repository;

public interface EntityRepository <E> {
    E save(E entity);
}
