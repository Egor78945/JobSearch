package com.example.api_gateway.enumeration;

public enum Cookie {
    REFRESH_TOKEN_COOKIE("refresh_token");
    private final String cookieName;

    Cookie(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieName() {
        return cookieName;
    }
}
