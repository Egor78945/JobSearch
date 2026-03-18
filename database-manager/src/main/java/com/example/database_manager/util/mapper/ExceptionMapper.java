package com.example.database_manager.util.mapper;

import com.example.database_manager.exception.ServiceException;
import io.grpc.Status;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMapper {
    public Status toStatus(ServiceException e) {
        return Status.fromCode(StatusCodeMapper.toCode(e.getCode()))
                .withDescription(e.getMessage())
                .withCause(e.getCause());
    }

    public Status toStatus(Exception e) {
        return Status.fromCode(StatusCodeMapper.toCode(500))
                .withDescription(e.getMessage())
                .withCause(e);
    }
}
