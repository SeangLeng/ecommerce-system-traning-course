package mptc.seangleng.ecommerce.payment.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentCommand(
        UUID orderId,
        UUID customerId,
        BigDecimal price
) {
}
