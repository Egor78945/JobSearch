package com.example.authentication_service.model.keycloak;

import com.example.authentication_service.model.user.security.UserAuthenticationModel;
import org.springframework.context.annotation.Primary;

public class KeycloakUser extends UserAuthenticationModel {
    protected String username;
    protected String userId;
    protected String realmName;
    protected String clientId;
    protected String[] groups;

    public KeycloakUser(String email, String username, String password, String userId, String realmName, String clientId, String[] groups) {
        super(email, password);
        this.username = username;
        this.userId = userId;
        this.realmName = realmName;
        this.clientId = clientId;
        this.groups = groups;
    }

    public KeycloakUser(String email, String username, String password, String realmName, String clientId, String[] groups) {
        super(email, password);
        this.username = username;
        this.realmName = realmName;
        this.clientId = clientId;
        this.groups = groups;
    }

    public KeycloakUser(String email, String username, String password, String realmName, String clientId) {
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
}
