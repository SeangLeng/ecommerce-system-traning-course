package mptc.seangleng.ecommerce.payment.port.output;

import mptc.seangleng.ecommerce.payment.entity.Payment;

public interface PaymentRepository {
    Payment savePayment(Payment payment);
}
