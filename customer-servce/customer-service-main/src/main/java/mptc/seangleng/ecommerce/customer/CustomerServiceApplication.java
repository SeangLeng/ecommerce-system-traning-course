package mptc.seangleng.ecommerce.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"mptc.seangleng.ecommerce.persistence"})
@EnableJpaRepositories(basePackages = {"mptc.seangleng.ecommerce.persistence"})
@SpringBootApplication(scanBasePackages = {
        "mptc.seangleng.ecommerce.customer",
        "mptc.seangleng.ecommerce.domain",
        "mptc.seangleng.ecommerce.persistence"
})
@EnableDiscoveryClient
public class CustomerServiceApplication {
    static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
