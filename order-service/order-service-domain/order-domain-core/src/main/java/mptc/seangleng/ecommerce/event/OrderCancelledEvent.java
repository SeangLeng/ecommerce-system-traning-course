package mptc.seangleng.ecommerce.event;

import mptc.seangleng.ecommerce.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(ZonedDateTime createdAt, Order order) {
        super(createdAt, order);
    }
}
