package com.shopeasy.presentation.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class
    })
    public void illegalException() {
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({
            EntityNotFoundException.class
    })
    public void notFoundException() {
    }
}
