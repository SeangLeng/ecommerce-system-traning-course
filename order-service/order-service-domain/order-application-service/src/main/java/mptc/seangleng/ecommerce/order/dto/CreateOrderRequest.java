package mptc.seangleng.ecommerce.order.dto;

import mptc.seangleng.ecommerce.order.ecommerce.valueobject.BusinessId;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CustomerId;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Money;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.StreetAddress;

public record CreateOrderRequest(
        CustomerId customerId,
        BusinessId businessId,
        StreetAddress deliveryAddress,
        Money price
) {
}
