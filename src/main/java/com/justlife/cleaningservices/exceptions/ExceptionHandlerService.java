package com.justlife.cleaningservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerService {

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ValidationMessage> handleException(Exception ex, WebRequest request) {
        ValidationMessage validationMessage = new ValidationMessage(ex.getMessage());
        return new ResponseEntity<>(validationMessage, HttpStatus.BAD_REQUEST);
    }


}
