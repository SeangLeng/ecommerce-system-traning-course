package mptc.seangleng.ecommerce.user.service.impl;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.user.dto.UserCreateRequest;
import mptc.seangleng.ecommerce.user.dto.UserResponse;
import mptc.seangleng.ecommerce.user.entity.UserEntity;
import mptc.seangleng.ecommerce.user.exception.PasswordMismatchException;
import mptc.seangleng.ecommerce.user.exception.UsernameConflictException;
import mptc.seangleng.ecommerce.user.mapper.UserMapper;
import mptc.seangleng.ecommerce.user.repository.UserRepository;
import mptc.seangleng.ecommerce.user.service.UserService;
import org.keycloak.admin.client.Keycloak;
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

    private final Keycloak keycloak;

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

        try (Response response = keycloak.realm("ecommerce")
                .users()
                .create(userRepresentation)) {
            log.info("Keycloak response: {}", response.getStatus());
            if (response.getStatus() == HttpStatus.CREATED.value()) {
                log.info("Created user");
            } else if (response.getStatus() == HttpStatus.CONFLICT.value()) {
                log.info("Keycloak user data conflict");
            } else {
                log.info("Cannot create user");
            }
        }

        return null;
    }

}