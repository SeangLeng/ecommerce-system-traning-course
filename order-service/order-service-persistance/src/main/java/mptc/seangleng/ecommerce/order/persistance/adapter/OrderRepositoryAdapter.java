package mptc.seangleng.ecommerce.order.persistance.adapter;

import mptc.seangleng.ecommerce.domain.port.output.OrderRepository;
import mptc.seangleng.ecommerce.entity.Order;

public class OrderRepositoryAdapter implements OrderRepository {

    @Override
    public Order saveOrder(Order order) {
        return null;
    }
}
