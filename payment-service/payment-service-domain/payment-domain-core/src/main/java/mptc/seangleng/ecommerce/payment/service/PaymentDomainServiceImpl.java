package mptc.seangleng.ecommerce.payment.service;

import mptc.seangleng.ecommerce.order.ecommerce.valueobject.CreditHistoryId;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.Money;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.PaymentStatus;
import mptc.seangleng.ecommerce.order.ecommerce.valueobject.TransactionType;
import mptc.seangleng.ecommerce.payment.entity.CreditEntry;
import mptc.seangleng.ecommerce.payment.entity.CreditHistory;
import mptc.seangleng.ecommerce.payment.entity.Payment;
import mptc.seangleng.ecommerce.payment.event.PaymentCancelledEvent;
import mptc.seangleng.ecommerce.payment.event.PaymentCompletedEvent;
import mptc.seangleng.ecommerce.payment.event.PaymentEvent;
import mptc.seangleng.ecommerce.payment.event.PaymentFailedEvent;
import mptc.seangleng.ecommerce.payment.exception.PaymentDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentDomainServiceImpl implements PaymentDomainService {

    @Override
    public PaymentEvent validateAndInitiatePayment(
            Payment payment,
            CreditEntry creditEntry,
            List<CreditHistory> creditHistories) {

        payment.validatePayment();
        payment.initializePayment();

        List<String> failureMessages = new ArrayList<>();

        if (creditEntry == null || !creditEntry.getTotalCreditAmount().isGreaterThanEqual(payment.getPrice())) {
            failureMessages.add("Customer does not have enough credit");
        }

        if (creditEntry != null && !creditEntry.getTotalCreditAmount().equals(getCreditHistoryTotal(creditHistories))) {
            failureMessages.add("Credit history is not consistent");
        }

        if (failureMessages.isEmpty()) {
            creditEntry.subtractCreditAmount(payment.getPrice());
            creditHistories.add(CreditHistory.builder()
                    .id(new CreditHistoryId(UUID.randomUUID()))
                    .customerId(payment.getCustomerId())
                    .amount(payment.getPrice())
                    .transactionType(TransactionType.DEBIT)
                    .build());
            payment.updateStatus(PaymentStatus.COMPLETED);
            return new PaymentCompletedEvent(payment, ZonedDateTime.now(ZoneId.of("UTC")), failureMessages);
        }

        payment.updateStatus(PaymentStatus.FAILED);
        return new PaymentFailedEvent(payment, ZonedDateTime.now(ZoneId.of("UTC")), failureMessages);
    }

    @Override
    public PaymentCancelledEvent cancelPayment(
            Payment payment,
            CreditEntry creditEntry,
            List<CreditHistory> creditHistories) {

        if (payment.getPaymentStatus() != PaymentStatus.COMPLETED) {
            throw new PaymentDomainException("Payment is not in correct state for cancel operation");
        }

        creditEntry.addCreditAmount(payment.getPrice());
        creditHistories.add(CreditHistory.builder()
                .id(new CreditHistoryId(UUID.randomUUID()))
                .customerId(payment.getCustomerId())
                .amount(payment.getPrice())
                .transactionType(TransactionType.CREDIT)
                .build());
        payment.updateStatus(PaymentStatus.CANCELLED);

        return new PaymentCancelledEvent(payment, ZonedDateTime.now(ZoneId.of("UTC")), new ArrayList<>());
    }

    private Money getCreditHistoryTotal(List<CreditHistory> creditHistories) {
        Money total = Money.ZERO;
        if (creditHistories == null) {
            return total;
        }
        for (CreditHistory history : creditHistories) {
            if (history.getTransactionType() == TransactionType.CREDIT) {
                total = total.add(history.getAmount());
            } else {
                total = total.subtract(history.getAmount());
            }
        }
        return total;
    }
}
