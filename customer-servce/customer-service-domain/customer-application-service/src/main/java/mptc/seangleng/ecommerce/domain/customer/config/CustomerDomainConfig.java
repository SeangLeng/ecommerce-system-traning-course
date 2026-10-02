package mptc.seangleng.ecommerce.domain.customer.config;

import mptc.seangleng.ecommerce.domain.service.CustomerDomainService;
import mptc.seangleng.ecommerce.domain.service.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDomainConfig {

    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
