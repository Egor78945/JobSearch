package com.example.database_manager.service.user.common;

import com.example.database_manager.repository.user.common.CommonUserRepository;
import com.example.database_manager.service.user.roles.common.CommonUsersRolesService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonUserRepositoryService implements CommonUserService{
    protected final CommonUserRepository commonUserRepository;
    protected final CommonUsersRolesService commonUsersRolesService;

    public CommonUserRepositoryService(CommonUserRepository commonUserRepository, CommonUsersRolesService commonUsersRolesService) {
        this.commonUserRepository = commonUserRepository;
        this.commonUsersRolesService = commonUsersRolesService;
    }

    @Override
    public void deleteByEmail(String email) {
        commonUsersRolesService.deleteAllByUserEmail(email);
        commonUserRepository.deleteByEmail(email);
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
