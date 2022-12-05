package com.example.testapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleException(EntityNotFoundException ex) { return handleBaseException(ex); }

    private ErrorResponse handleBaseException(Exception ex) {
        var response = new ErrorResponse();
        response.setText(ex.getMessage());
        return response;
    }
}
