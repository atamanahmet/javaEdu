package com.contacts.contacts_restfull.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ErrorResponse {

    private List<String> errorMessages;

    private String timestamp;

    public ErrorResponse(String message) {
        this.errorMessages.add(message);
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm"));
    }

    public ErrorResponse(List<String> errorMessages) {
        this.errorMessages = errorMessages;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm"));

    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
