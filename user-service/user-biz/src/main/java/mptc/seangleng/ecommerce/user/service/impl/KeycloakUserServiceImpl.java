package mptc.seangleng.ecommerce.user.service.impl;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.user.config.KeycloakAdminClientConfig;
import mptc.seangleng.ecommerce.user.exception.KeycloakException;
import mptc.seangleng.ecommerce.user.service.KeycloakService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserServiceImpl implements KeycloakService {
    private final Keycloak keycloak;
    private final KeycloakAdminClientConfig adminClientConfig;

    @Override
    public void createKeycloakUser(UserRepresentation userRepresentation) {
        try (Response response = keycloak.realm("ecommerce")
                .users()
                .create(userRepresentation)) {
            log.info("Keycloak response: {}", response.getStatus());
            if (response.getStatus() == HttpStatus.CREATED.value()) {
                log.info("Created user");
            } else {
                throw new KeycloakException(HttpStatus.BAD_REQUEST, "Create user failed");
            }
        }
    }

    @Override
    public UserRepresentation getKeycloakUserUsername(String username) {
        return keycloak.realm("ecommerce")
                .users()
                .search(username, true)
                .stream().findFirst().orElseThrow(() -> new KeycloakException(HttpStatus.NOT_FOUND, "User in keycloak not found"));
    }

    @Override
    public void updateKeycloakUser(String userId, UserRepresentation userRepresentation) {
        try {
            keycloak.realm(adminClientConfig.REALM_NAME)
                    .users()
                    .get(userId)
                    .update(userRepresentation);
        } catch (Exception e) {
            log.error("Update keycloak user failed: {}", e.getMessage());
            throw new KeycloakException(HttpStatus.BAD_REQUEST, "Update user failed");
        }
    }

    @Override
    public void deleteKeycloakUser(String userId) {
        try (Response response = keycloak.realm(adminClientConfig.REALM_NAME)
                .users()
                .delete(userId)) {
            int status = response.getStatus();
            if (status == HttpStatus.NO_CONTENT.value() || status == HttpStatus.NOT_FOUND.value()) {
                log.info("Deleted keycloak user: {}", userId);
                return;
            }
            throw new KeycloakException(HttpStatus.BAD_REQUEST, "Delete user failed");
        }
    }

    @Override
    public UserRepresentation getKeycloakUserById(String userId) {
        return keycloak.realm(adminClientConfig.REALM_NAME)
                .users()
                .get(userId)
                .toRepresentation();
    }
}
