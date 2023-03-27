package com.switchfully.order.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatedINSSNumberException extends RuntimeException {
    public DuplicatedINSSNumberException(String message) {
        super(message);
    }
}
