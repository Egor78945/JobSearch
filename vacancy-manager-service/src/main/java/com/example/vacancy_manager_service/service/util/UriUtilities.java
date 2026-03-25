package com.example.vacancy_manager_service.service.util;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class UriUtilities {
    public static URI buildFrom(String uri, Map<String, String> parameters) {
        if (parameters == null) {
            return URI.create(uri);
        }
        StringBuilder result = new StringBuilder(uri).append("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            result.append(entry.getKey()).append("=").append(entry.getValue());
            result.append("&");
        }
        if (result.charAt(result.length() - 1) == '&' || result.charAt(result.length() - 1) == '?') {
            result.deleteCharAt(result.length() - 1);
        }

        return URI.create(result.toString());
    }

    public static Map<String, String> encodeParams(Map<String, String> params, Charset charset) {
        Map<String, String> result = new HashMap<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.put(entry.getKey(), URLEncoder.encode(entry.getValue(), charset));
        }

        return result;
    }
}
