package mptc.seangleng.ecommerce.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"mptc.seangleng.ecommerce.payment.persistence"})
@EnableJpaRepositories(basePackages = {"mptc.seangleng.ecommerce.payment.persistence"})
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {
    static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
