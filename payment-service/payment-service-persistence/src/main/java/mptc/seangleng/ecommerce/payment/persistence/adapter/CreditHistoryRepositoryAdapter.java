package mptc.seangleng.ecommerce.payment.persistence.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerId;
import mptc.seangleng.ecommerce.payment.entity.CreditHistory;
import mptc.seangleng.ecommerce.payment.persistence.entity.CreditHistoryEntity;
import mptc.seangleng.ecommerce.payment.persistence.mapper.CreditHistoryPersistenceMapper;
import mptc.seangleng.ecommerce.payment.persistence.repository.CreditHistoryJpaRepository;
import mptc.seangleng.ecommerce.payment.port.output.CreditHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CreditHistoryRepositoryAdapter implements CreditHistoryRepository {
    private final CreditHistoryJpaRepository creditHistoryJpaRepository;
    private final CreditHistoryPersistenceMapper creditHistoryPersistenceMapper;

    @Override
    public CreditHistory save(CreditHistory creditHistory) {
        CreditHistoryEntity creditHistoryEntity =
                creditHistoryPersistenceMapper.creditHistoryToCreditHistoryEntity(creditHistory);
        CreditHistoryEntity savedCreditHistoryEntity = creditHistoryJpaRepository.save(creditHistoryEntity);
        return creditHistoryPersistenceMapper.creditHistoryEntityToCreditHistory(savedCreditHistoryEntity);
    }

    @Override
    public List<CreditHistory> findByCustomerId(CustomerId customerId) {
        return creditHistoryJpaRepository.findByCustomerId(customerId.value()).stream()
                .map(creditHistoryPersistenceMapper::creditHistoryEntityToCreditHistory)
                .toList();
    }
}
