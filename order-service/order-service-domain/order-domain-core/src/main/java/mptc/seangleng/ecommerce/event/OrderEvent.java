package mptc.seangleng.ecommerce.event;

import mptc.seangleng.ecommerce.ecommerce.event.DomainEvent;
import mptc.seangleng.ecommerce.entity.Order;

import java.time.ZonedDateTime;

public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;

    public OrderEvent(ZonedDateTime createdAt, Order order) {
        this.createdAt = createdAt;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
