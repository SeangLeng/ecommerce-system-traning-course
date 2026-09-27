package mptc.seangleng.ecommerce.payment.event;

import mptc.seangleng.ecommerce.payment.entity.Payment;

import java.time.ZonedDateTime;
import java.util.List;

/*
    Name conversion <resource_name+past_tense+Event>, it needs to follow the English's grammar.
    Example: PaymentCompletedEvent, PaymentCancelledEvent etc.
*/
public class PaymentCompletedEvent extends PaymentEvent {

    public PaymentCompletedEvent(Payment payment, ZonedDateTime createdAt, List<String> failureMessages) {
        super(payment, createdAt, failureMessages);
    }
}
