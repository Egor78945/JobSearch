package com.example.api_gateway.configuration.util.mapper;

public interface StringMapper {
    <F> String mapToString(F from);
    <T> T mapFromString(String from, Class<T> to);

}
