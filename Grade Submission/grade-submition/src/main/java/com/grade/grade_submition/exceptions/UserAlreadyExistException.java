package com.grade.grade_submition.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String errorContent) {
        super("User already exist.");
    }
}
