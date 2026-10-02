package mptc.seangleng.ecommerce.business.port.output;

import mptc.seangleng.ecommerce.business.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
