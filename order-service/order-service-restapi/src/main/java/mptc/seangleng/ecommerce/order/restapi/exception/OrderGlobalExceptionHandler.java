package mptc.seangleng.ecommerce.order.restapi.exception;

import mptc.seangleng.ecommerce.order.exception.OrderDomainException;
import mptc.seangleng.ecommerce.restapi.dto.RestApiErrorResponse;
import mptc.seangleng.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderDomainException.class)
    public RestApiErrorResponse<?> orderDomainExceptionHandler(
            OrderDomainException ex
    ) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Data validation failed!")
                .detail(ex.getMessage())
                .build();
    }
}
