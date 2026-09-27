package mptc.seangleng.ecommerce.order;

import mptc.seangleng.ecommerce.order.service.OrderDomainService;
import mptc.seangleng.ecommerce.order.service.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
