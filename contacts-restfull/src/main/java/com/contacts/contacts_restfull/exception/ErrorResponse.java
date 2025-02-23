package com.contacts.contacts_restfull.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private List<String> message = new ArrayList<>();

    private String timestamp;

    public ErrorResponse(String message) {
        this.message.add(message);
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm"));
    }

    public ErrorResponse(List<String> message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm"));

    }

    public List<String> getmessage() {
        return message;
    }

    public void setmessage(List<String> message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
