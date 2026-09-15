package mptc.seangleng.ecommerce.restapi.exception;

import mptc.seangleng.ecommerce.restapi.dto.FieldErrorResponse;
import mptc.seangleng.ecommerce.restapi.dto.RestApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private List<FieldErrorResponse> extractFieldErrors(
            List<FieldError> fieldErrors
    ) {
        return fieldErrors.stream().map(
                fieldError -> new FieldErrorResponse(
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getDefaultMessage()
                )
        ).toList();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestApiErrorResponse<?> handleException(
            MethodArgumentNotValidException ex
    ) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Data validation failed!")
                .detail(this.extractFieldErrors(ex.getBindingResult().getFieldErrors()))
                .build();
    }
}
