package mptc.seangleng.ecommerce.domain.port.dto;

import mptc.seangleng.ecommerce.ecommerce.valueobject.BusinessId;
import mptc.seangleng.ecommerce.ecommerce.valueobject.CustomerId;
import mptc.seangleng.ecommerce.ecommerce.valueobject.Money;
import mptc.seangleng.ecommerce.ecommerce.valueobject.StreetAddress;

public record CreateOrderRequest(
        CustomerId customerId,
        BusinessId businessId,
        StreetAddress deliveryAddress,
        Money price
) {
}
