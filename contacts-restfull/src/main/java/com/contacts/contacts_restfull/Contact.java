package com.contacts.contacts_restfull;

import java.util.UUID;

public class Contact {
    private String id;
    private String name;
    private String phoneNumber;

    public Contact() {
        this.id = UUID.randomUUID().toString();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
