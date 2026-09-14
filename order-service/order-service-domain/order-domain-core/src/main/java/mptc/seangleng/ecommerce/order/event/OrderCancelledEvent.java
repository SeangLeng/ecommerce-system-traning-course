package mptc.seangleng.ecommerce.order.event;

import mptc.seangleng.ecommerce.order.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(ZonedDateTime createdAt, Order order) {
        super(createdAt, order);
    }
}
