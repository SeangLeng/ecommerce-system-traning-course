package mptc.seangleng.ecommerce.payment.dto;

import mptc.seangleng.ecommerce.order.ecommerce.valueobject.PaymentStatus;

import java.util.UUID;

public record CreatePaymentResult(
        UUID paymentId,
        PaymentStatus paymentStatus
) {
}
