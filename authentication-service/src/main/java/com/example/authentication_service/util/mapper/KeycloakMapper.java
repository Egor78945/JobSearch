package com.example.authentication_service.util.mapper;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public class KeycloakMapper {
    public static UserRepresentation buildUserRepresentation(String username, String email) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(username);
        userRepresentation.setEmail(email);
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        return userRepresentation;
    }

    public static CredentialRepresentation buildCredentialRepresentation(String credential, String credentialType) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(credentialType);
        credentialRepresentation.setValue(credential);
        credentialRepresentation.setTemporary(false);
        return credentialRepresentation;
    }
}
