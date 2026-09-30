package mptc.seangleng.ecommerce.payment.service;

import mptc.seangleng.ecommerce.payment.entity.CreditEntry;
import mptc.seangleng.ecommerce.payment.entity.CreditHistory;
import mptc.seangleng.ecommerce.payment.entity.Payment;
import mptc.seangleng.ecommerce.payment.event.PaymentCancelledEvent;
import mptc.seangleng.ecommerce.payment.event.PaymentEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(
            Payment payment,
            CreditEntry creditEntry,
            List<CreditHistory> creditHistories);

    PaymentCancelledEvent cancelPayment(
            Payment payment,
            CreditEntry creditEntry,
            List<CreditHistory> creditHistories);
}
