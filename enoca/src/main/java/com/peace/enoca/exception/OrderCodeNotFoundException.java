package com.peace.enoca.exception;

public class OrderCodeNotFoundException extends RuntimeException {
    public OrderCodeNotFoundException(String message) {
        super(message);
    }
}
