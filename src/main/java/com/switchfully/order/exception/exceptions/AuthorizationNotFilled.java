package com.switchfully.order.exception.exceptions;

public class AuthorizationNotFilled extends RuntimeException {
    public AuthorizationNotFilled(String message) {
        super(message);
    }
}
