package mptc.seangleng.ecommerce.user.service.impl;


import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.user.config.KeycloakAdminClientConfig;
import mptc.seangleng.ecommerce.user.exception.KeycloakException;
import mptc.seangleng.ecommerce.user.service.KeycloakRoleService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakRoleServiceImpl implements KeycloakRoleService {
    private final Keycloak keycloak;
    private final KeycloakAdminClientConfig config;

    @Override
    public void assignRole(String userId, String roleNameWithDelimiter) {
        String[] roleNameesList = roleNameWithDelimiter.split(",");
        List<RoleRepresentation> roleRepresentations =  Arrays.stream(roleNameesList)
                .map(roleName -> keycloak.realm(config.REALM_NAME)
                        .roles()
                        .get(roleName)
                        .toRepresentation()
                ).toList();

        keycloak.realm(config.REALM_NAME)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(roleRepresentations);
    }

    @Override
    public List<UserRepresentation> getUsersByRoleName(String roleName) {
        try {
            return keycloak.realm(config.REALM_NAME)
                    .roles()
                    .get(roleName)
                    .getUserMembers();
        } catch (NotFoundException ex) {
            throw new KeycloakException(HttpStatus.NOT_FOUND, "Role not found");
        }
    }
}
