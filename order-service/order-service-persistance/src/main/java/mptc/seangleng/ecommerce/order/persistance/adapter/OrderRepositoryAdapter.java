package mptc.seangleng.ecommerce.order.persistance.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.order.persistance.entity.OrderEntity;
import mptc.seangleng.ecommerce.order.persistance.mapper.OrderPersistenceMapper;
import mptc.seangleng.ecommerce.order.persistance.repository.OrderJpaRepository;
import mptc.seangleng.ecommerce.order.port.output.OrderRepository;
import mptc.seangleng.ecommerce.order.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Order saveOrder(Order order) {
        // Map Order to OrderEntity
        OrderEntity orderEntity = orderPersistenceMapper.orderToOrderEntity(order);

        // Map OrderEntity to Order
        return orderPersistenceMapper.orderEntityToOrder(orderJpaRepository.save(orderEntity));
    }
}
