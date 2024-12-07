package com.peace.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(final CustomerNotFoundException customerNotFoundException) {

        return ResponseEntity.status(404).body(customerNotFoundException.getMessage());
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(final ProductNotFoundException productNotFoundException) {

        return ResponseEntity.status(404).body(productNotFoundException.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(final EmailAlreadyExistsException emailAlreadyExistsException) {

        Map<String, String> errors = new HashMap<>();

        errors.put("error", emailAlreadyExistsException.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(emailAlreadyExistsException.getMessage());
    }
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<String> handleInsufficientStockException(final InsufficientStockException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
