package com.grade.grade_submition.exceptions;

public class ContentNotFoundException extends RuntimeException {
    public ContentNotFoundException(String errorContent, Object id) {
        super(errorContent + " not found with id \"" + id + "\"");
    }
}
