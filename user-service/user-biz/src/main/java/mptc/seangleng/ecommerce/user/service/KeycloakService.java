package mptc.seangleng.ecommerce.user.service;

import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakService {
    void createKeycloakUser(UserRepresentation userRepresentation);
    UserRepresentation getKeycloakUserUsername(String username);

    void updateKeycloakUser(String userId, UserRepresentation userRepresentation);

    UserRepresentation getKeycloakUserById(String userId);

    void deleteKeycloakUser(String userId);
}
