package com.example.authentication_service.service.grpc.client;

public abstract class GrpcClientService<S> {
    protected final S blockingStub;

    public GrpcClientService(S blockingStub) {
        this.blockingStub = blockingStub;
    }
}
