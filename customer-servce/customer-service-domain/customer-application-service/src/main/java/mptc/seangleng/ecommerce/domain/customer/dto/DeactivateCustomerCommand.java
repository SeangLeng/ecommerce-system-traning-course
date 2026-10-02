package mptc.seangleng.ecommerce.domain.customer.dto;

import java.util.UUID;

public record DeactivateCustomerCommand(
        UUID customerId
) {
}
