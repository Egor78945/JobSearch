package com.example.api_gateway.configuration.util.mapper;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class JsonMapper implements StringMapper{
    private final Gson gson;

    public JsonMapper(Gson gson) {
        this.gson = gson;
    }


    @Override
    public <F> String mapToString(F from) {
        return gson.toJson(from);
    }

    @Override
    public <T> T mapFromString(String from, Class<T> to) {
        return gson.fromJson(from, to);
    }
}
