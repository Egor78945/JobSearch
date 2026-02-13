package com.example.authentication_service.service.user.registration;

import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.service.user.common.CommonUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserGeneralRegistrationService implements RegistrationService<UserModel> {
    protected final UserKeycloakRegistrationService userKeycloakRegistrationService;
    protected final UserProtoRegistrationService userProtoRegistrationService;
    protected final CommonUserService commonUserService;

    public UserGeneralRegistrationService(UserKeycloakRegistrationService userKeycloakRegistrationService, UserProtoRegistrationService userProtoRegistrationService, CommonUserService commonUserService) {
        this.userKeycloakRegistrationService = userKeycloakRegistrationService;
        this.userProtoRegistrationService = userProtoRegistrationService;
        this.commonUserService = commonUserService;
    }

    @Override
    public void register(UserModel registerRequest) {
        userProtoRegistrationService.register(registerRequest);
        try {
            userKeycloakRegistrationService.register(registerRequest);
        } catch (Exception e) {
            commonUserService.deleteByEmail(registerRequest.getEmail());
        }
    }
}
