package mptc.seangleng.ecommerce.business.service;

import mptc.seangleng.ecommerce.business.entity.Business;
import mptc.seangleng.ecommerce.business.event.OrderApprovalEvent;
import mptc.seangleng.ecommerce.business.event.OrderApprovedEvent;
import mptc.seangleng.ecommerce.business.event.OrderRejectedEvent;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.OrderApprovalStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class BusinessDomainServiceImpl implements BusinessDomainService {

    @Override
    public OrderApprovalEvent validateOrder(Business business, List<String> failureMessages) {
        business.validateOrder(failureMessages);

        if (failureMessages.isEmpty()) {
            business.constructOrderApproval(OrderApprovalStatus.APPROVED);
            return new OrderApprovedEvent(business.getOrderApproval(), business.getId(),
                    failureMessages, ZonedDateTime.now(ZoneId.of("UTC")));
        }

        business.constructOrderApproval(OrderApprovalStatus.REJECTED);
        return new OrderRejectedEvent(business.getOrderApproval(), business.getId(),
                failureMessages, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
