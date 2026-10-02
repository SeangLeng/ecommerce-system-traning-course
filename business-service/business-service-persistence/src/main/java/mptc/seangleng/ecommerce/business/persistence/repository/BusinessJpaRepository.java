package mptc.seangleng.ecommerce.business.persistence.repository;

import mptc.seangleng.ecommerce.business.persistence.entity.BusinessEntity;
import mptc.seangleng.ecommerce.business.persistence.entity.BusinessIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface BusinessJpaRepository extends JpaRepository<BusinessEntity, BusinessIdEntity> {
    List<BusinessEntity> findByBusinessIdAndProductIdIn(UUID businessId, Collection<UUID> productIds);
}
