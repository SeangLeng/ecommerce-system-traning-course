package mptc.seangleng.ecommerce.payment.persistence.repository;

import mptc.seangleng.ecommerce.payment.persistence.entity.CreditEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreditEntryJpaRepository extends JpaRepository<CreditEntryEntity, UUID> {
    Optional<CreditEntryEntity> findByCustomerId(UUID customerId);
}
