package mptc.seangleng.ecommerce.domain.port.output;

import mptc.seangleng.ecommerce.entity.Order;

public interface OrderRepository {
    Order saveOrder(Order order);
}
