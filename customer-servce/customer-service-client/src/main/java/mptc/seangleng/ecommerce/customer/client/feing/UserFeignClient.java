package mptc.seangleng.ecommerce.customer.client.feing;

import mptc.seangleng.ecommerce.customer.client.dto.UserResponse;
import mptc.seangleng.ecommerce.customer.client.fallback.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service", // the name is exactly the same nacos config.
        path = "/api/users",
        fallback = UserFeignClientFallback.class
)
public interface UserFeignClient {

    // Fetch user by id from user service
    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable String id);
}
