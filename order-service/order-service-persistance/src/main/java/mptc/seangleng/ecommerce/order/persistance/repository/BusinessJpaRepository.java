package mptc.seangleng.ecommerce.order.persistance.repository;

import mptc.seangleng.ecommerce.order.persistance.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface BusinessJpaRepository extends JpaRepository<BusinessEntity, UUID> {
    List<BusinessEntity> findByBusinessIdAndProductIdIn(UUID businessId, Collection<UUID> productIds);
}
