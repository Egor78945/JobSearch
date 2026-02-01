package com.example.authentication_service.enumeration;

public enum UserStatus {
    STATUS_ACTIVE(1), STATUS_BLOCK(2), STATUS_FREEZE(3), STATUS_DELETE(4);
    private final long id;

    UserStatus(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
