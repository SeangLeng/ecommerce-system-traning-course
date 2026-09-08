package mptc.seangleng.ecommerce.event;

import mptc.seangleng.ecommerce.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {

    public OrderPaidEvent(ZonedDateTime createdAt, Order order) {
        super(createdAt, order);
    }
}
