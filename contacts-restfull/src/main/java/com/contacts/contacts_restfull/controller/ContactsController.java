package com.contacts.contacts_restfull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.contacts.contacts_restfull.Contact;
import com.contacts.contacts_restfull.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ContactsController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public ResponseEntity<List<Contact>> getIndexPage() {

        return ResponseEntity.ok(contactService.getContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable String id) {
        HttpStatus result = contactService.isIdValid(id);

        return new ResponseEntity<>(contactService.getContactById(id), result);

    }

    @PostMapping("/contact")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        HttpStatus result = contactService.saveContact(contact);
        return new ResponseEntity<Contact>(contactService.getContactById(contact.getId()), result);

    }

    @PutMapping("contact/{id}")
    public ResponseEntity<Contact> putContact(@PathVariable String id, @RequestBody Contact contact) {

        HttpStatus result = contactService.isIdValid(id);

        return new ResponseEntity<>(contactService.updateContact(id, contact), result);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id) {
        return new ResponseEntity<>(contactService.deleteContact(id));
    }
}
