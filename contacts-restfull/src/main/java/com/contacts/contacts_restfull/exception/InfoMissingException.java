package com.contacts.contacts_restfull.exception;

public class InfoMissingException extends RuntimeException {
    public InfoMissingException() {
        super("Some info missing. Send with complete info.");
    }
}
