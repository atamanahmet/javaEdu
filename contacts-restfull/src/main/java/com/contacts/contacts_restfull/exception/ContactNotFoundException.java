package com.contacts.contacts_restfull.exception;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String id) {
        super("Id '" + id + "' does not exist.");
    }
}
