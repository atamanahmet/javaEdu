package com.contacts.contacts_restfull.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse {
    private String message;

    private String timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm"));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
