package com.switchfully.order.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class INSSNumberNotFilledException extends RuntimeException {
    public INSSNumberNotFilledException(String message) {
        super(message);
    }
}
