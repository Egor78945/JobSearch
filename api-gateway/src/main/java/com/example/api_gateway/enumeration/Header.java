package com.example.api_gateway.enumeration;

public enum Header {
    REFRESH_TOKEN_HEADER("X-Refresh-Token");
    private final String headerName;

    Header(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}
