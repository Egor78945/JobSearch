package com.example.authentication_service.service.user.registration;

import com.example.authentication_service.model.user.security.UserAuthenticationModel;
import com.example.authentication_service.service.RegistrationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserGeneralRegistrationService implements RegistrationService<UserAuthenticationModel> {
    protected final UserKeycloakRegistrationService userKeycloakRegistrationService;
    protected final UserProtoRegistrationService userProtoRegistrationService;

    public UserGeneralRegistrationService(UserKeycloakRegistrationService userKeycloakRegistrationService, UserProtoRegistrationService userProtoRegistrationService) {
        this.userKeycloakRegistrationService = userKeycloakRegistrationService;
        this.userProtoRegistrationService = userProtoRegistrationService;
    }

    @Override
    public void register(UserAuthenticationModel registerRequest) {
        userKeycloakRegistrationService.register(registerRequest);
        userProtoRegistrationService.register(registerRequest);
    }
}
