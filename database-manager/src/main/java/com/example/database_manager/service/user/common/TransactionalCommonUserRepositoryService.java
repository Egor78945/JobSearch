package com.example.database_manager.service.user.common;

import com.example.database_manager.repository.user.common.CommonUserRepository;
import com.example.database_manager.service.user.roles.common.CommonUsersRolesService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class TransactionalCommonUserRepositoryService extends CommonUserRepositoryService {
    public TransactionalCommonUserRepositoryService(CommonUserRepository commonUserRepository, CommonUsersRolesService commonUsersRolesService) {
        super(commonUserRepository, commonUsersRolesService);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteByEmail(String email) {
        super.deleteByEmail(email);
    }
}
