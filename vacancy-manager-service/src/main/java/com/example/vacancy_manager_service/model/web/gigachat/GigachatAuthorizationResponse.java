package com.example.vacancy_manager_service.model.web.gigachat;

public class GigachatAuthorizationResponse {
    private String access_token;
    private long expires_at;

    public GigachatAuthorizationResponse(String access_token, long expires_at) {
        this.access_token = access_token;
        this.expires_at = expires_at;
    }

    public GigachatAuthorizationResponse() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(long expires_at) {
        this.expires_at = expires_at;
    }
}
