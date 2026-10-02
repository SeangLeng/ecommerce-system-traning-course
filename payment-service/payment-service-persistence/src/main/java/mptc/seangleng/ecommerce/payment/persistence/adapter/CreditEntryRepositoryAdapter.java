package mptc.seangleng.ecommerce.payment.persistence.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerId;
import mptc.seangleng.ecommerce.payment.entity.CreditEntry;
import mptc.seangleng.ecommerce.payment.persistence.entity.CreditEntryEntity;
import mptc.seangleng.ecommerce.payment.persistence.mapper.CreditEntryPersistenceMapper;
import mptc.seangleng.ecommerce.payment.persistence.repository.CreditEntryJpaRepository;
import mptc.seangleng.ecommerce.payment.port.output.CreditEntryRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreditEntryRepositoryAdapter implements CreditEntryRepository {
    private final CreditEntryJpaRepository creditEntryJpaRepository;
    private final CreditEntryPersistenceMapper creditEntryPersistenceMapper;

    @Override
    public CreditEntry findByCustomerId(CustomerId customerId) {
        return creditEntryJpaRepository.findByCustomerId(customerId.value())
                .map(creditEntryPersistenceMapper::creditEntryEntityToCreditEntry)
                .orElse(null);
    }

    @Override
    public CreditEntry save(CreditEntry creditEntry) {
        CreditEntryEntity creditEntryEntity = creditEntryPersistenceMapper.creditEntryToCreditEntryEntity(creditEntry);
        CreditEntryEntity savedCreditEntryEntity = creditEntryJpaRepository.save(creditEntryEntity);
        return creditEntryPersistenceMapper.creditEntryEntityToCreditEntry(savedCreditEntryEntity);
    }
}
