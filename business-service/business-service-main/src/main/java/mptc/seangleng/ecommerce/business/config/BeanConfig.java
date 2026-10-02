package mptc.seangleng.ecommerce.business.config;

import mptc.seangleng.ecommerce.business.service.BusinessDomainService;
import mptc.seangleng.ecommerce.business.service.BusinessDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public BusinessDomainService businessDomainService() {
        return new BusinessDomainServiceImpl();
    }
}
