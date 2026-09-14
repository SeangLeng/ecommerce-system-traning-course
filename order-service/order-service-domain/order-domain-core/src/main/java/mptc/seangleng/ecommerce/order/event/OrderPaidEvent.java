package mptc.seangleng.ecommerce.order.event;

import mptc.seangleng.ecommerce.order.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {

    public OrderPaidEvent(ZonedDateTime createdAt, Order order) {
        super(createdAt, order);
    }
}
