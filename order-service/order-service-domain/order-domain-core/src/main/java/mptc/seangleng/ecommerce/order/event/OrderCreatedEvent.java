package mptc.seangleng.ecommerce.order.event;

import mptc.seangleng.ecommerce.order.entity.Order;

import java.time.ZonedDateTime;

/*
    Name conversion <resource_name+past_tense+Event>, it needs to follow the English's grammar.
    Example: OrderCreatedEvent, OrderSucceedEvent etc.
*/
public class OrderCreatedEvent extends OrderEvent {
    private Order order;

    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(createdAt, order);
    }
}
