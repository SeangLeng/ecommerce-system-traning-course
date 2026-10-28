package mptc.seangleng.ecommerce.user.exception;

import mptc.seangleng.ecommerce.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
