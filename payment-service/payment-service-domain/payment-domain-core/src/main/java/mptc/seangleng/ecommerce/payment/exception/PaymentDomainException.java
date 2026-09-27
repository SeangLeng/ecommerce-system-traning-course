package mptc.seangleng.ecommerce.payment.exception;

import mptc.seangleng.ecommerce.order.ecommerce.exception.DomainException;

public class PaymentDomainException extends DomainException {
    public PaymentDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentDomainException(String message) {
        super(message);
    }
}
