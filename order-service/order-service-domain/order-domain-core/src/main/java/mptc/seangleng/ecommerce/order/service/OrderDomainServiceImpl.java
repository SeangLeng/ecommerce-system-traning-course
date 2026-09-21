package mptc.seangleng.ecommerce.order.service;

import mptc.seangleng.ecommerce.order.entity.Business;
import mptc.seangleng.ecommerce.order.entity.Order;
import mptc.seangleng.ecommerce.order.event.OrderCancelledEvent;
import mptc.seangleng.ecommerce.order.event.OrderCreatedEvent;
import mptc.seangleng.ecommerce.order.event.OrderPaidEvent;

import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Business business) {
        return null;
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        return null;
    }

    @Override
    public void approveOrder(Order order) {

    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        return null;
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {

    }
}
