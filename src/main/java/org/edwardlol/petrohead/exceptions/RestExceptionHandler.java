package org.edwardlol.petrohead.exceptions;

import org.edwardlol.petrohead.exceptions.error.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = ApiError.buider()
                .httpStatus(HttpStatus.BAD_REQUEST)
//                .message("Malformed JSON request")
                .message(ex.getMessage())
                .debugMessage(ex)
                .build();

        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {

        ApiError apiError = ApiError.buider()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .debugMessage(ex)
                .build();

        return buildResponseEntity(apiError);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = ApiError.buider()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .debugMessage(new Throwable("am i "))
                .build();

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return ResponseEntity.status(apiError.getHttpStatus()).body(apiError);
//        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

}
