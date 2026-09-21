package mptc.seangleng.ecommerce.order.persistance.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.order.entity.Business;
import mptc.seangleng.ecommerce.order.persistance.mapper.BusinessPersistenceMapper;
import mptc.seangleng.ecommerce.order.persistance.repository.BusinessJpaRepository;
import mptc.seangleng.ecommerce.order.port.output.BusinessRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {
    private final BusinessJpaRepository businessJpaRepository;
    private final BusinessPersistenceMapper businessPersistenceMapper;

    @Override
    public Optional<Business> findById(UUID id) {
        return businessJpaRepository.findById(id).map(businessPersistenceMapper::businessToBusinessEntity);
    }
}
