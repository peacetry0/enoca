package com.peace.enoca.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super("Customer Not Found");
    }
}
