package com.example.api_gateway.model;

import java.util.Arrays;

public class JwtModel {
    private final String user_uuid;
    private final RealmAccessModel realm_access;

    public JwtModel(String user_uuid, RealmAccessModel realm_access) {
        this.user_uuid = user_uuid;
        this.realm_access = realm_access;
    }

    public String getUser_uuid() {
        return user_uuid;
    }

    public RealmAccessModel getRealm_access() {
        return realm_access;
    }

    @Override
    public String toString() {
        return "JwtModel{" +
                "user_uuid='" + user_uuid + '\'' +
                ", realm_access=" + realm_access +
                '}';
    }

    public static class RealmAccessModel {
        private final String[] roles;

        public RealmAccessModel(String[] roles) {
            this.roles = roles;
        }

        public String[] getRoles() {
            return roles;
        }

        @Override
        public String toString() {
            return "RealmAccessModel{" +
                    "roles=" + Arrays.toString(roles) +
                    '}';
        }
    }
}
