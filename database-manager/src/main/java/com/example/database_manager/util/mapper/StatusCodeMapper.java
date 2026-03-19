package com.example.database_manager.util.mapper;

import io.grpc.Status;

import java.util.HashMap;
import java.util.Map;

public class StatusCodeMapper {
    private static final Map<Integer, Status.Code> map;

    static {
        map = new HashMap<>();
        map.put(404, Status.Code.NOT_FOUND);
        map.put(500, Status.Code.INTERNAL);
        map.put(401, Status.Code.UNAUTHENTICATED);
        map.put(409, Status.Code.ALREADY_EXISTS);
        map.put(400, Status.Code.INVALID_ARGUMENT);
    }

    public static Status.Code toCode(int code) {
        return map.get(code);
    }
}
