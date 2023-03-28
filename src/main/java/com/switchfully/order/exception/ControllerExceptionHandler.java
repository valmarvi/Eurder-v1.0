package com.switchfully.order.exception;

import com.switchfully.order.exception.exceptions.CustomerNotFoundException;
import com.switchfully.order.exception.exceptions.ItemNotFoundException;
import com.switchfully.order.exception.exceptions.DuplicateEmailException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    protected void illegalArgumentException(IllegalArgumentException ex,
                                            HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage());
        response.sendError(SC_BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    protected void duplicatedINSSNumberException(DuplicateEmailException ex,
                                                 HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage());
        response.sendError(SC_BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected void itemNotFoundException(ItemNotFoundException ex) {
        logger.error(ex.getMessage());
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    protected void customerNotFoundException(CustomerNotFoundException ex) {
        logger.error(ex.getMessage());
    }

//
//
//    @ExceptionHandler(UnauthorizedAccessException.class)
//    protected void unauthorizedAccessException(UnauthorizedAccessException ex, HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
//    }
//
//    @ExceptionHandler(AuthorizationNotFilled.class)
//    protected void authorizationNotFilled(AuthorizationNotFilled ex, HttpServletResponse response) throws IOException {
//        logger.error(ex.getMessage());
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }
}
