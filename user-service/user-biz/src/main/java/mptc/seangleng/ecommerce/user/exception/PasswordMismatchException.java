package mptc.seangleng.ecommerce.user.exception;

import mptc.seangleng.ecommerce.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends GlobalException {
    public PasswordMismatchException(HttpStatus status, String message) {
        super(status, message);
    }
}

