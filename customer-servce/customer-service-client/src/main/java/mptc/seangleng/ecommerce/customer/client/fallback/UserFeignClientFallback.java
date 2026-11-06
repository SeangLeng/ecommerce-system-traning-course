package mptc.seangleng.ecommerce.customer.client.fallback;

import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.customer.client.dto.UserResponse;
import mptc.seangleng.ecommerce.customer.client.feing.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public UserResponse getUserById(String id) {
        log.error("Fallback UserFeignClientFallback, with id: {}", id);
        return null;
    }
}
