package com.example.authentication_service.service.user.registration;

import com.example.authentication_service.exception.AuthenticationException;
import com.example.authentication_service.model.keycloak.KeycloakUserModel;
import com.example.authentication_service.model.user.UserModel;
import com.example.authentication_service.model.user.UserRegistrationResponse;
import com.example.authentication_service.service.RegistrationService;
import com.example.authentication_service.service.user.common.CommonUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserGeneralRegistrationService implements RegistrationService<UserModel, UserRegistrationResponse> {
    protected final UserKeycloakRegistrationService userKeycloakRegistrationService;
    protected final UserProtoRegistrationService userProtoRegistrationService;
    protected final CommonUserService commonUserService;

    public UserGeneralRegistrationService(UserKeycloakRegistrationService userKeycloakRegistrationService, UserProtoRegistrationService userProtoRegistrationService, CommonUserService commonUserService) {
        this.userKeycloakRegistrationService = userKeycloakRegistrationService;
        this.userProtoRegistrationService = userProtoRegistrationService;
        this.commonUserService = commonUserService;
    }

    @Override
    public UserRegistrationResponse register(UserModel registerRequest) {
        String userUuid = userProtoRegistrationService.register(registerRequest).getUuid();
        try {
            String keycloakUserId = userKeycloakRegistrationService.register(new KeycloakUserModel(registerRequest.getEmail(), registerRequest.getEmail(), userUuid, registerRequest.getPassword())).getUserId();
            return new UserRegistrationResponse(userUuid, keycloakUserId);
        } catch (Exception e) {
            commonUserService.deleteByEmail(registerRequest.getEmail());
            throw new AuthenticationException(String.format("registration failed: %s", e.getMessage()));
        }
    }
}
