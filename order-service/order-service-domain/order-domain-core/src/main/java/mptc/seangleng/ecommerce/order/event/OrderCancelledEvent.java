package mptc.seangleng.ecommerce.order.event;

import mptc.seangleng.ecommerce.order.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(createdAt, order);
    }
}
