package mptc.seangleng.ecommerce.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.user.dto.UserCreateRequest;
import mptc.seangleng.ecommerce.user.dto.UserResponse;
import mptc.seangleng.ecommerce.user.dto.UserUpdateRequest;
import mptc.seangleng.ecommerce.user.entity.UserEntity;
import mptc.seangleng.ecommerce.user.exception.PasswordMismatchException;
import mptc.seangleng.ecommerce.user.exception.UserNotFoundException;
import mptc.seangleng.ecommerce.user.exception.UsernameConflictException;
import mptc.seangleng.ecommerce.user.mapper.UserMapper;
import mptc.seangleng.ecommerce.user.repository.UserRepository;
import mptc.seangleng.ecommerce.user.service.KeycloakRoleService;
import mptc.seangleng.ecommerce.user.service.KeycloakService;
import mptc.seangleng.ecommerce.user.service.UserService;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final KeycloakService keycloakService;
    private final KeycloakRoleService keycloakRoleService;

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userMapper.toEntity(userCreateRequest);

        if (userRepository.existsByUsername(userCreateRequest.username())) {
            throw new UsernameConflictException(HttpStatus.CONFLICT,
                    "Username already exists");
        }

        if (!userCreateRequest.password().equals(userCreateRequest.confirmPassword())) {
            throw new PasswordMismatchException(HttpStatus.BAD_REQUEST,
                    "Password does not match");
        }

        UserRepresentation userRepresentation = userMapper.toRepresentation(userCreateRequest);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userCreateRequest.password());

        userRepresentation.setCredentials(List.of(credentialRepresentation));
        keycloakService.createKeycloakUser(userRepresentation);

        UserRepresentation createdUser = keycloakService.getKeycloakUserUsername(userRepresentation.getUsername());

        // assign role
        keycloakRoleService.assignRole(createdUser.getId(), userCreateRequest.roleId());

        // Saving data into database.
        userEntity.setId(createdUser.getId());
        userRepository.save(userEntity);
        log.info("User created: {}", createdUser);

        return userMapper.toRepresentation(userEntity, createdUser);
    }

    @Override
    public UserResponse update(String id, UserUpdateRequest userUpdateRequest) {
        // check user exist or not
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));

        // mapper to update.
        userMapper.toEntity(userUpdateRequest, userEntity);
        userRepository.save(userEntity);

        // update keycloak user
        UserRepresentation userRepresentation = keycloakService.getKeycloakUserUsername(userEntity.getUsername());

        if (!(userUpdateRequest.familyName() != null && !userUpdateRequest.familyName().isEmpty())) {
            if (userUpdateRequest.familyName() != null) {
                userRepresentation.setFirstName(userUpdateRequest.familyName());
            }

            if  (userUpdateRequest.givenName() != null) {
                userRepresentation.setLastName(userUpdateRequest.givenName());
            }
        }

        return userMapper.toRepresentation(userEntity,  userRepresentation);
    }

    @Override
    public UserResponse getById(String userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));

        UserRepresentation userRepresentation = keycloakService.getKeycloakUserById(userId);
        return userMapper.toRepresentation(userEntity, userRepresentation);
    }
}