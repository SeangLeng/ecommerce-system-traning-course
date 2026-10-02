package mptc.seangleng.ecommerce.business.persistence.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.business.entity.OrderApproval;
import mptc.seangleng.ecommerce.business.persistence.entity.OrderApprovalEntity;
import mptc.seangleng.ecommerce.business.persistence.mapper.OrderApprovalPersistenceMapper;
import mptc.seangleng.ecommerce.business.persistence.repository.OrderApprovalJpaRepository;
import mptc.seangleng.ecommerce.business.port.output.OrderApprovalRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderApprovalRepositoryAdapter implements OrderApprovalRepository {

    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final OrderApprovalPersistenceMapper orderApprovalPersistenceMapper;

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        OrderApprovalEntity orderApprovalEntity =
                orderApprovalPersistenceMapper.orderApprovalToOrderApprovalEntity(orderApproval);
        return orderApprovalPersistenceMapper.orderApprovalEntityToOrderApproval(
                orderApprovalJpaRepository.save(orderApprovalEntity));
    }
}
