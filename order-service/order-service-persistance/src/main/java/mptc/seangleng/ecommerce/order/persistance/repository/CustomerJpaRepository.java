package mptc.seangleng.ecommerce.order.persistance.repository;

import mptc.seangleng.ecommerce.order.persistance.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {

}
