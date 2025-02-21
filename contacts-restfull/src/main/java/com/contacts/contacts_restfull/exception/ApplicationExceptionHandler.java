package com.contacts.contacts_restfull.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<Object> handleNoContactFoundException(ContactNotFoundException contactNotFoundException) {
        return new ResponseEntity<>(new ErrorResponse("Id cannot be found, Please check id."),
                HttpStatus.NOT_FOUND);
    }
}
