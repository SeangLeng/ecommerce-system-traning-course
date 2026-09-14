package mptc.seangleng.ecommerce.domain.port.dto;

import mptc.seangleng.ecommerce.ecommerce.valueobject.OrderId;

public record RecordOrderResponse(
        OrderId orderId
) {
}
