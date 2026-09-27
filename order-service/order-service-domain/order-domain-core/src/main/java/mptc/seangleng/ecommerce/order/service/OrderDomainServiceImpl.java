package mptc.seangleng.ecommerce.order.service;

import mptc.seangleng.ecommerce.order.entity.Business;
import mptc.seangleng.ecommerce.order.entity.Order;
import mptc.seangleng.ecommerce.order.entity.Product;
import mptc.seangleng.ecommerce.order.event.OrderCancelledEvent;
import mptc.seangleng.ecommerce.order.event.OrderCreatedEvent;
import mptc.seangleng.ecommerce.order.event.OrderPaidEvent;
import mptc.seangleng.ecommerce.order.exception.OrderDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Business business) {
        // validate business

        if (!business.getActive()) {
            throw new OrderDomainException("Order is not active.");
        }

        // set product information
        order.getOrderItems().forEach(orderItem -> {
            business.getProducts().forEach(businessProduct -> {
                Product orderProduct = orderItem.getProduct();
                if (orderProduct.equals(businessProduct)) {
                    orderProduct.updateConfirmedNameAndPrice(businessProduct.getName(), businessProduct.getPrice());
                }
            });
        });

        order.validateOrder();
        order.initializeOrder();

        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);

        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
    }
}
