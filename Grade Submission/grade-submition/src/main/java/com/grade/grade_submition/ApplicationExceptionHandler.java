package com.grade.grade_submition;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.grade.grade_submition.exceptions.ContentNotFoundException;
import com.grade.grade_submition.exceptions.UserAlreadyExistException;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    // @Override
    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<Object> handleContentNotFoundException(ContentNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
