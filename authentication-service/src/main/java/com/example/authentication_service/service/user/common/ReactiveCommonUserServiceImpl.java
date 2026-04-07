package com.example.authentication_service.service.user.common;

import com.example.authentication_service.service.user.grpc.client.UserGrpcClientService;
import com.example.authentication_service.util.mapper.GrpcMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ReactiveCommonUserServiceImpl implements ReactiveCommonUserService {
    protected final UserGrpcClientService userGrpcClientService;

    public ReactiveCommonUserServiceImpl(UserGrpcClientService userGrpcClientService) {
        this.userGrpcClientService = userGrpcClientService;
    }

    @Override
    public Mono<Void> deleteByEmail(String email) {
        return Mono.fromRunnable(() -> userGrpcClientService.deleteByEmail(GrpcMapper.mapTo(email)))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
