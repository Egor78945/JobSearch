package com.example.api_gateway.service.processor;

import org.springframework.http.HttpMessage;
import org.springframework.http.server.ServerHttpResponse;

public interface HttpResponseProcessor<R extends HttpMessage> {
    void process(R response);
}
