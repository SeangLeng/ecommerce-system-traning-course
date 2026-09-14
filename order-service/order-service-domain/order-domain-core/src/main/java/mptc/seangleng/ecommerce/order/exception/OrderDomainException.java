package mptc.seangleng.ecommerce.order.exception;

import mptc.seangleng.ecommerce.order.ecommerce.exception.DomainException;

public class OrderDomainException extends DomainException {
    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDomainException(String message) {
        super(message);
    }
}
