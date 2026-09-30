package mptc.seangleng.ecommerce.payment;

import mptc.seangleng.ecommerce.payment.service.PaymentDomainService;
import mptc.seangleng.ecommerce.payment.service.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
