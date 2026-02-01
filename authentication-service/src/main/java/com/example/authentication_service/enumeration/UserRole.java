package com.example.authentication_service.enumeration;

public enum UserRole {
    ROLE_USER(1), ROLE_ADMIN(2);
    private final long id;

    UserRole(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
