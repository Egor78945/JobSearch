package com.example.authentication_service.model.user;

public class UserRegistrationResponse {
    private String userUuid;
    private String keycloakUserId;

    public UserRegistrationResponse(String userUuid, String keycloakUserId) {
        this.userUuid = userUuid;
        this.keycloakUserId = keycloakUserId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public String getKeycloakUserId() {
        return keycloakUserId;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public void setKeycloakUserId(String keycloakUserId) {
        this.keycloakUserId = keycloakUserId;
    }
}
