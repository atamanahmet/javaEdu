package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Validated
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping("/")
    public ResponseEntity<List<Contact>> getContact() {
        return new ResponseEntity<>(service.getContacts(), HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<Contact> postMethodName(@Valid @RequestBody Contact contact)
            throws Exception {

        service.saveContact(contact);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
