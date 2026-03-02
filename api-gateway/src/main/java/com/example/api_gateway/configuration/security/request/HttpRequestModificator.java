package com.example.api_gateway.configuration.security.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpRequest;

public interface HttpRequestModificator<R extends HttpMessage> {
    R addHeaders(R request, HttpHeaders headers);

}
