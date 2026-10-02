package mptc.seangleng.ecommerce.business;

import lombok.extern.slf4j.Slf4j;
import mptc.seangleng.ecommerce.business.persistence.entity.BusinessEntity;
import mptc.seangleng.ecommerce.business.persistence.repository.BusinessJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@EntityScan(basePackages = {"mptc.seangleng.ecommerce.business.persistence"})
@EnableJpaRepositories(basePackages = {"mptc.seangleng.ecommerce.business.persistence"})
@SpringBootApplication
@EnableDiscoveryClient
public class BusinessServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(BusinessServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner seedDatabase(BusinessJpaRepository businessRepository) {
        return args -> {
            if (businessRepository.count() > 0) {
                return;
            }

            UUID businessId = UUID.randomUUID();

            BusinessEntity firstProduct = new BusinessEntity();
            firstProduct.setBusinessId(businessId);
            firstProduct.setProductId(UUID.randomUUID());
            firstProduct.setBusinessName("Office Supply");
            firstProduct.setBusinessActive(true);
            firstProduct.setProductName("Office Chair");
            firstProduct.setProductPrice(new BigDecimal("120"));
            firstProduct.setProductAvailable(true);
            businessRepository.save(firstProduct);

            BusinessEntity secondProduct = new BusinessEntity();
            secondProduct.setBusinessId(businessId);
            secondProduct.setProductId(UUID.randomUUID());
            secondProduct.setBusinessName("Office Supply");
            secondProduct.setBusinessActive(true);
            secondProduct.setProductName("Desk Lamp");
            secondProduct.setProductPrice(new BigDecimal("35"));
            secondProduct.setProductAvailable(true);
            businessRepository.save(secondProduct);

            log.info("Seeded businessId={}, productIds={}, {}",
                    businessId, firstProduct.getProductId(), secondProduct.getProductId());
        };
    }
}
