package mptc.seangleng.ecommerce.entity;

import mptc.seangleng.ecommerce.ecommerce.entity.AggregateRoot;
import mptc.seangleng.ecommerce.ecommerce.valueobject.*;
import mptc.seangleng.ecommerce.exception.OrderDomainException;

import java.util.List;
import java.util.UUID;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final BusinessId businessId;
    private final StreetAddress delivery;
    private final Money price;
    private final List<OrderItem> orderItems;

    // No final cuz we need to change later!
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;



    // Critical business logic
    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateInitialOrder() {
        if (orderStatus != null || super.getId() == null) {
            throw new OrderDomainException("Order is not in correct status for initialization");
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isAmountGreaterThanZero()) {
            throw new OrderDomainException("Total price cannot be greater than zero");
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().amount() +
                    " is not valid for product: " + orderItem.getProduct().getId().value());
        }
    }

    private void validateItemsPrice() {
        Money orderItemsTotalPrice = orderItems.stream().map(orderitem -> {
            validateItemPrice(orderitem);
            return orderitem.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!price.equals(orderItemsTotalPrice)) {
            throw new OrderDomainException("Total price: " + price.amount()
                    + " is not equal to order items total price: " + orderItemsTotalPrice.amount());
        }
    }

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        int itemCount = 1;
        for (OrderItem item : orderItems) {
            item.initializeOrderItem(super.getId(), new OrderItemId(itemCount++));
        }
    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Order is not in correct state for pay operation");
        }
        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for approve operation");
        }
        orderStatus = OrderStatus.APPROVE;
    }

    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for init cancel operation");
        }
        orderStatus = OrderStatus.CANCELING;
        updateFailureMessages(failureMessages);
    }

    public void cancel(List<String> failureMessages) {
        if (!(orderStatus == OrderStatus.CANCELING || orderStatus == OrderStatus.PENDING)) {
            throw new OrderDomainException("Order is not in correct state for cancel operation");
        }
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (failureMessages != null && this.failureMessages != null) {
            this.failureMessages.addAll(
                    failureMessages.stream().filter(message -> !message.isBlank()).toList()
            );
        }

        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public BusinessId getBusinessId() {
        return businessId;
    }

    public StreetAddress getDelivery() {
        return delivery;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    private Order(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        businessId = builder.businessId;
        delivery = builder.delivery;
        price = builder.money;
        orderItems = builder.orderItems;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private BusinessId businessId;
        private StreetAddress delivery;
        private Money money;
        private List<OrderItem> orderItems;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder businessId(BusinessId val) {
            businessId = val;
            return this;
        }

        public Builder delivery(StreetAddress val) {
            delivery = val;
            return this;
        }

        public Builder money(Money val) {
            money = val;
            return this;
        }

        public Builder orderItems(List<OrderItem> val) {
            orderItems = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
