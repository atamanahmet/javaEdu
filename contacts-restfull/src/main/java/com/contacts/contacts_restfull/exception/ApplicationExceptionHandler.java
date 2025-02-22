package com.contacts.contacts_restfull.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<Object> handleNoContactFoundException(ContactNotFoundException contactNotFoundException) {
        return new ResponseEntity<>(new ErrorResponse("Id cannot be found, Please check id."),
                HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorList = new ArrayList<>();
        ex.getAllErrors().forEach(error -> errorList.add(error.getDefaultMessage()));

        return new ResponseEntity<>(new ErrorResponse(errorList),
                HttpStatus.NOT_ACCEPTABLE);
    }

}

// @ExceptionHandler(IllegalArgumentException.class)
// public ResponseEntity<Object> handle(InfoMissingException
// infoMissingException) {
// return new ResponseEntity<>(new ErrorResponse("Some fields empty. Send
// request with complete info"),
// HttpStatus.NOT_ACCEPTABLE);
// }
