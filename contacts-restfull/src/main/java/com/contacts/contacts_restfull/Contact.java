package com.contacts.contacts_restfull;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class Contact {

    private String id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Number cannot be empty")
    private String phoneNumber;

    public Contact() {
        this.id = UUID.randomUUID().toString();
    }

    // public Contact(String id, String name, String phoneNumber) {
    // this.id = id;
    // this.name = name;
    // this.phoneNumber = phoneNumber;
    // }

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
