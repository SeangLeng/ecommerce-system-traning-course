package mptc.seangleng.ecommerce.order.service;


import mptc.seangleng.ecommerce.order.entity.Business;
import mptc.seangleng.ecommerce.order.entity.Order;
import mptc.seangleng.ecommerce.order.event.OrderCancelledEvent;
import mptc.seangleng.ecommerce.order.event.OrderCreatedEvent;
import mptc.seangleng.ecommerce.order.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Business business);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
