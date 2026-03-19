package com.example.authentication_service.util.mapper;

import io.grpc.Status;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class StatusCodeMapper {
    private static final Map<Integer, HttpStatus> map;

    static {
        map = new HashMap<>();
        map.put(5, HttpStatus.NOT_FOUND);
        map.put(13, HttpStatus.INTERNAL_SERVER_ERROR);
        map.put(16, HttpStatus.UNAUTHORIZED);
        map.put(6, HttpStatus.CONFLICT);
        map.put(3, HttpStatus.BAD_REQUEST);
    }

    public static HttpStatus toCode(int code) {
        return map.get(code);
    }
}
