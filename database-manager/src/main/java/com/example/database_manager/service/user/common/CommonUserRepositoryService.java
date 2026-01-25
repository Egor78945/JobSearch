package com.example.database_manager.service.user.common;

import com.example.database_manager.repository.user.common.CommonUserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonUserRepositoryService implements CommonUserService{
    protected final CommonUserRepository commonUserRepository;

    public CommonUserRepositoryService(CommonUserRepository commonUserRepository) {
        this.commonUserRepository = commonUserRepository;
    }

    @Override
    public boolean existsById(Long id) {
        return commonUserRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return commonUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return commonUserRepository.existsByUuid(uuid);
    }
}
