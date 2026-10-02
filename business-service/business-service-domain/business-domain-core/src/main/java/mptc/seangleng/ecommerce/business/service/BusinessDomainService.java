package mptc.seangleng.ecommerce.business.service;

import mptc.seangleng.ecommerce.business.entity.Business;
import mptc.seangleng.ecommerce.business.event.OrderApprovalEvent;

import java.util.List;

public interface BusinessDomainService {
    OrderApprovalEvent validateOrder(Business business, List<String> failureMessages);
}
