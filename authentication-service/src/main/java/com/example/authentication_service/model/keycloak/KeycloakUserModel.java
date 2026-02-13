package com.example.authentication_service.model.keycloak;

import com.example.authentication_service.model.user.UserModel;

import java.util.Arrays;

public class KeycloakUserModel extends UserModel {
    protected String username;
    protected String userId;
    protected String realmName;
    protected String clientId;
    protected String[] groups;

    public KeycloakUserModel(String email, String username, String password, String realmName, String clientId, String[] groups) {
        super(email, password);
        this.username = username;
        this.realmName = realmName;
        this.clientId = clientId;
        this.groups = groups;
    }

    public KeycloakUserModel(String email, String username, String password, String realmName, String clientId) {
        super(email, password);
        this.username = username;
        this.realmName = realmName;
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealmName() {
        return realmName;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "KeycloakUser{" +
                "username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", realmName='" + realmName + '\'' +
                ", clientId='" + clientId + '\'' +
                ", groups=" + Arrays.toString(groups) +
                "} " + super.toString();
    }
}
