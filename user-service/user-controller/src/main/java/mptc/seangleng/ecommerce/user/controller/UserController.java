package mptc.seangleng.ecommerce.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.user.dto.UserCreateRequest;
import mptc.seangleng.ecommerce.user.dto.UserResponse;
import mptc.seangleng.ecommerce.user.dto.UserUpdateRequest;
import mptc.seangleng.ecommerce.user.mapper.UserMapper;
import mptc.seangleng.ecommerce.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateRequest userCreateRequest){
        return userService.create(userCreateRequest);
    }

    //  If Circuit Breaker open it will fall back.
    @SentinelResource(
            value = "getUsers",
            fallback = "getUsersFallback",
            blockHandler = "getUsersBlock"
    )
    @GetMapping
    public List<UserResponse> getUsers() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userService.getUsers();
    }

    public List<UserResponse> getUsersFallback(Throwable t) {
        return Collections.emptyList();
    }

    public List<UserResponse> getUsersFallBlock(BlockException t) {
        return Collections.emptyList();
    }

    @GetMapping("/roles/{roleName}")
    public List<UserResponse> getUsersByRoleName(@PathVariable String roleName) {
        return userService.getUsersByRoleName(roleName);
    }

    @GetMapping("/username/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable String id, @Valid @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.update(id, userUpdateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
