package com.example.demo;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class Contact {

    private String id;

    @NotBlank(message = "name can not be blank")
    private String name;

    @NotBlank(message = "number can not be blank")
    private String number;

    public Contact() {
        this.id = UUID.randomUUID().toString();
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
