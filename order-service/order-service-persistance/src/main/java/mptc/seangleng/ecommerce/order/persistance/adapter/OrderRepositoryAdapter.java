package mptc.seangleng.ecommerce.order.persistance.adapter;

import lombok.RequiredArgsConstructor;
import mptc.seangleng.ecommerce.order.persistance.repository.OrderJpaRepository;
import mptc.seangleng.ecommerce.order.port.output.OrderRepository;
import mptc.seangleng.ecommerce.order.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {
    @Override
    public Order saveOrder(Order order) {
        return null;
    }
}
