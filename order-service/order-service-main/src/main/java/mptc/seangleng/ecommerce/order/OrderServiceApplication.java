package mptc.seangleng.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"mptc.seangleng.ecommerce.order.persistance"})
@EnableJpaRepositories(basePackages = {"mptc.seangleng.ecommerce.order.persistance"})
@SpringBootApplication
public class OrderServiceApplication {
    static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}