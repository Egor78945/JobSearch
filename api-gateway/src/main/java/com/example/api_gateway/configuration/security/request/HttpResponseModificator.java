package com.example.api_gateway.configuration.security.request;

import org.springframework.http.HttpMessage;
import org.springframework.http.ResponseCookie;

public interface HttpResponseModificator<R extends HttpMessage> {
    R addCookie(R request, ResponseCookie cookie);
}
