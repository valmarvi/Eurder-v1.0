package com.switchfully.order.exception;

import com.switchfully.order.exception.exceptions.CustomerNotFoundException;
import com.switchfully.order.exception.exceptions.ItemNotFoundException;
import com.switchfully.order.exception.exceptions.DuplicateEmailException;
import com.switchfully.order.exception.exceptions.UnauthorizedAccessException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;

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

    @ExceptionHandler(UnauthorizedAccessException.class)
    protected void unauthorizedAccessException(UnauthorizedAccessException ex,
                                               HttpServletResponse response) throws IOException {
        response.sendError(SC_FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected void itemNotFoundException(ItemNotFoundException ex) {
        logger.error(ex.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    protected void customerNotFoundException(CustomerNotFoundException ex) {
        logger.error(ex.getMessage());
    }
}
