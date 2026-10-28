package mptc.seangleng.ecommerce.user.service;

import mptc.seangleng.ecommerce.user.dto.UserCreateRequest;
import mptc.seangleng.ecommerce.user.dto.UserResponse;

public interface UserService {
    UserResponse create(UserCreateRequest userCreateRequest);
}
