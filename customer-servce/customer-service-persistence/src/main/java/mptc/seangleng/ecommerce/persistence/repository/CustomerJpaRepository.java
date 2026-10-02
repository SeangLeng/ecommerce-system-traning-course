package mptc.seangleng.ecommerce.persistence.repository;

import mptc.seangleng.ecommerce.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
