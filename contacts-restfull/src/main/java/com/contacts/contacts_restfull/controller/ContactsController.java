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
        ResponseEntity<List<Contact>> response = ResponseEntity.ok(contactService.getContacts());
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable String id) {

        if (contactService.isIdValid(id)) {
            return ResponseEntity.ok(contactService.getContactById(id));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Contact> getByName(@PathVariable String name) {

        if (contactService.isNameValid(name)) {
            return ResponseEntity.ok(contactService.getContactByName(name));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contact")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        if (contactService.isContactBodyValid(contact)) {

            HttpStatus status = contactService.saveContact(contact);

            return new ResponseEntity<Contact>(contactService.getContactById(contact.getId()), status);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("contact/{id}")
    public ResponseEntity<Contact> putContact(@PathVariable String id, @RequestBody Contact contact) {
        if (contactService.isContactBodyValid(contact)) {
            HttpStatus status = contactService.updateContact(id, contact);
            if (status == HttpStatus.OK) {
                return ResponseEntity.ok(contactService.getContactById(id));
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        }

    }

    @DeleteMapping("delete/{id}")
    public HttpStatus deleteContact(@PathVariable String id) {
        if (contactService.isIdValid(id)) {
            return contactService.deleteContact(id);
        }
        return HttpStatus.NOT_FOUND;
    }

    /*
     * ResponseEntity<>
     */

    // @GetMapping("/")
    // public ResponseEntity<List<Contact>> getIndexPage() {
    // ResponseEntity<List<Contact>> response = new
    // ResponseEntity<>(contactService.getContacts(), HttpStatus.OK);
    // return response;
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Contact> getById(@PathVariable String id) {
    // ResponseEntity<Contact> response = new
    // ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    // return response;
    // }

    // @GetMapping("/name/{name}")
    // public ResponseEntity<Contact> getByName(@PathVariable String name) {
    // System.out.println("asd " + contactService.getContactByName(name).getName());

    // ResponseEntity<Contact> response = new
    // ResponseEntity<>(contactService.getContactByName(name), HttpStatus.OK);
    // return response;
    // }

}
