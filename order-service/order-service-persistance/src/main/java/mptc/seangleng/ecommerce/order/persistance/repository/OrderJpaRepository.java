package mptc.seangleng.ecommerce.order.persistance.repository;

import mptc.seangleng.ecommerce.order.persistance.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Benefit of using spring JPA
 * 1. Boilerplate code
 * 2. Abstraction repository
 * 3. Deride query method (Auto generate SQL)
 * 4. Object Relational Mapping (ORM)
 * 5. Specification (dynamic query)
 */
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}
