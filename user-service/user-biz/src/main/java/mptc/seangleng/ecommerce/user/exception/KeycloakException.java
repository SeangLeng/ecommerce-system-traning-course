package mptc.seangleng.ecommerce.user.exception;

import mptc.seangleng.ecommerce.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class KeycloakException extends GlobalException {

    public KeycloakException(HttpStatus status, String message) {
        super(status, message);
    }
}
