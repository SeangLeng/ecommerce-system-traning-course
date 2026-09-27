package mptc.seangleng.ecommerce.order.dto;

import mptc.seangleng.ecommerce.order.ecommerce.valueobject.OrderId;

import java.util.UUID;

public record CreateOrderResult(
        UUID orderId
) {
}
