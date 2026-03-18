package com.example.database_manager.controller.grpc.aspect;

import com.example.database_manager.exception.ServiceException;
import com.example.database_manager.util.mapper.ExceptionMapper;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcControllerAspect {
    private final ExceptionMapper exceptionMapper;

    public GrpcControllerAspect(ExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
    }

    @GrpcExceptionHandler(ServiceException.class)
    public Status handleServerException(ServiceException e) {
        return exceptionMapper.toStatus(e);
    }

    @GrpcExceptionHandler(Exception.class)
    public Status handleException(Exception e) {
        return exceptionMapper.toStatus(e);
    }

}
