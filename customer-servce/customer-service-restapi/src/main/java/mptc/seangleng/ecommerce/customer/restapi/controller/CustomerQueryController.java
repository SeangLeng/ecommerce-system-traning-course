package mptc.seangleng.ecommerce.customer.restapi.controller;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.customer.client.dto.UserResponse;
import mptc.seangleng.ecommerce.customer.client.feing.UserFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerQueryController {
    private final UserFeignClient userFeignClient;

    @GetMapping("/check-user/{id}")
    UserResponse checkUser(@PathVariable String id) {
        return userFeignClient.getUserById(id);
    }
}
