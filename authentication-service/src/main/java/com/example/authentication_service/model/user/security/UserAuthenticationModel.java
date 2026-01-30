package com.example.authentication_service.model.user.security;

import java.util.Objects;

public class UserAuthenticationModel {
    private String email;
    private String password;

    public UserAuthenticationModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthenticationModel that = (UserAuthenticationModel) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "UserRegistrationModel{" +
                "email='" + email + '\'' +
                '}';
    }
}
