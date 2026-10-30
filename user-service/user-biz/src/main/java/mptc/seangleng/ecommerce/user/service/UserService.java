package mptc.seangleng.ecommerce.user.service;

import mptc.seangleng.ecommerce.user.dto.UserCreateRequest;
import mptc.seangleng.ecommerce.user.dto.UserResponse;
import mptc.seangleng.ecommerce.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {
    UserResponse create(UserCreateRequest userCreateRequest);

    List<UserResponse> getUsersByRoleName(String roleName);

    List<UserResponse> getUsers();

    UserResponse getUserById(String id);

    UserResponse getUserByUsername(String username);

    UserResponse update(String id, UserUpdateRequest userUpdateRequest);

    void delete(String id);
}
