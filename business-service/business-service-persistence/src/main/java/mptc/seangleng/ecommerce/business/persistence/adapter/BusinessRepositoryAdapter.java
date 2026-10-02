package mptc.seangleng.ecommerce.business.persistence.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.business.entity.Business;
import mptc.seangleng.ecommerce.business.persistence.entity.BusinessEntity;
import mptc.seangleng.ecommerce.business.persistence.mapper.BusinessPersistenceMapper;
import mptc.seangleng.ecommerce.business.persistence.repository.BusinessJpaRepository;
import mptc.seangleng.ecommerce.business.port.output.BusinessRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {

    private final BusinessJpaRepository businessJpaRepository;
    private final BusinessPersistenceMapper businessPersistenceMapper;

    @Override
    public Optional<Business> findBusinessInformation(Business business) {
        List<UUID> businessProducts = businessPersistenceMapper.businessToBusinessProducts(business);
        List<BusinessEntity> businessEntities = businessJpaRepository.findByBusinessIdAndProductIdIn(
                business.getId().value(),
                businessProducts
        );
        return Optional.of(businessPersistenceMapper.businessEntityToBusiness(businessEntities));
    }
}
