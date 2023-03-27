package com.switchfully.order.exception;

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

//    @ExceptionHandler(BookNotFoundException.class)
//    protected void bookNotFoundException(BookNotFoundException ex,
//                                         HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
//    }
//
//    @ExceptionHandler(INSSNumberNotFilledException.class)
//    protected void inssNumberNotFilletException(INSSNumberNotFilledException ex,
//                                                HttpServletResponse response) throws IOException {
//        logger.error(ex.getMessage());
//        response.sendError(SC_BAD_REQUEST, ex.getMessage());
//    }
//
//    @ExceptionHandler(DuplicatedINSSNumberException.class)
//    protected void duplicatedINSSNumberException(DuplicatedINSSNumberException ex,
//                                                 HttpServletResponse response) throws IOException {
//        logger.error(ex.getMessage());
//        response.sendError(SC_BAD_REQUEST, ex.getMessage());
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected void illegalArgumentException(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

//    @ExceptionHandler(AuthorNotFoundException.class)
//    protected void authorNotFoundException(AuthorNotFoundException ex, HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
//    }
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
