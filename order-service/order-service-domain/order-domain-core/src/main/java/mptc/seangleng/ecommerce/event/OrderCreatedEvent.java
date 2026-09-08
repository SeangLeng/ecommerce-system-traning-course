package mptc.seangleng.ecommerce.event;

import mptc.seangleng.ecommerce.entity.Order;

import java.time.ZonedDateTime;

/*
    Name conversion <resource_name+past_tense+Event>, it needs to follow the English's grammar.
    Example: OrderCreatedEvent, OrderSucceedEvent etc.
*/
public class OrderCreatedEvent extends OrderEvent {
    private Order order;

    public OrderCreatedEvent(ZonedDateTime createdAt, Order order) {
        super(createdAt, order);
    }
}
