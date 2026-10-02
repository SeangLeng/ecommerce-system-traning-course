package mptc.seangleng.ecommerce.business.event;

import mptc.seangleng.ecommerce.business.entity.OrderApproval;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.BusinessId;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderRejectedEvent extends OrderApprovalEvent {
    public OrderRejectedEvent(OrderApproval orderApproval,
                              BusinessId businessId,
                              List<String> failureMessages,
                              ZonedDateTime createdAt) {
        super(orderApproval, businessId, failureMessages, createdAt);
    }
}
