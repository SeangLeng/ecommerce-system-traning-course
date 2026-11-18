package mptc.seangleng.ecommerce.customer.config;

import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerCacheConfig {

    @Bean
    RedisCacheManagerBuilderCustomizer customerCacheManagerCustomizer() {
        return builder -> builder.transactionAware();
    }
}
