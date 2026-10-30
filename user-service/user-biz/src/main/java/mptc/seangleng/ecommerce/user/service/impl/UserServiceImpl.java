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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public List<UserResponse> getUsersByRoleName(String roleName) {
        List<UserRepresentation> members = keycloakRoleService.getUsersByRoleName(roleName);
        Map<String, UserEntity> entities = userRepository.findAllById(
                        members.stream().map(UserRepresentation::getId).toList())
                .stream()
                .collect(Collectors.toMap(UserEntity::getId, Function.identity()));

        return members.stream()
                .filter(member -> entities.containsKey(member.getId()))
                .map(member -> userMapper.toRepresentation(entities.get(member.getId()), member))
                .toList();
    }

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(String id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));
        return toResponse(userEntity);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));
        return toResponse(userEntity);
    }

    @Override
    public UserResponse update(String id, UserUpdateRequest userUpdateRequest) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));

        userMapper.toEntity(userUpdateRequest, userEntity);
        userRepository.save(userEntity);

        UserRepresentation userRepresentation = keycloakService.getKeycloakUserById(id);
        boolean nameChanged = false;

        if (userUpdateRequest.familyName() != null && !userUpdateRequest.familyName().isBlank()) {
            userRepresentation.setFirstName(userUpdateRequest.familyName());
            nameChanged = true;
        }
        if (userUpdateRequest.givenName() != null && !userUpdateRequest.givenName().isBlank()) {
            userRepresentation.setLastName(userUpdateRequest.givenName());
            nameChanged = true;
        }
        if (nameChanged) {
            keycloakService.updateKeycloakUser(id, userRepresentation);
        }

        return userMapper.toRepresentation(userEntity, userRepresentation);
    }

    @Override
    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found");
        }
        keycloakService.deleteKeycloakUser(id);
        userRepository.deleteById(id);
        log.info("User deleted: {}", id);
    }

    private UserResponse toResponse(UserEntity userEntity) {
        UserRepresentation userRepresentation = keycloakService.getKeycloakUserById(userEntity.getId());
        return userMapper.toRepresentation(userEntity, userRepresentation);
    }
}