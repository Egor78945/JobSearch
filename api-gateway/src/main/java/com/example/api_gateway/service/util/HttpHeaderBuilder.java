package com.example.api_gateway.service.util;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class HttpHeaderBuilder {
    public static HttpHeaders buildFrom(Map<String, String> headerMap) {
        return HttpHeaders.copyOf(MultiValueMap.fromSingleValue(headerMap));
    }
}
