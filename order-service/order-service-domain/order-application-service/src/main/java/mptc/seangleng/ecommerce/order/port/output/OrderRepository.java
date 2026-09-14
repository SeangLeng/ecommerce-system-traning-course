package mptc.seangleng.ecommerce.order.port.output;

import mptc.seangleng.ecommerce.order.entity.Order;

public interface OrderRepository {
    Order saveOrder(Order order);
}
