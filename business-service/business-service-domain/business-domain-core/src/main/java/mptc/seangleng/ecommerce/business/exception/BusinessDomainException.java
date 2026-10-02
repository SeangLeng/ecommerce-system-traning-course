package mptc.seangleng.ecommerce.business.exception;

import mptc.seangleng.ecommerce.order.ecommerce.exception.DomainException;

public class BusinessDomainException extends DomainException {

    public BusinessDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessDomainException(String message) {
        super(message);
    }
}
