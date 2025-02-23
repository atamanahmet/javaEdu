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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Contact Controller", description = "Create and Rretrieve contacts")
@RestController
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @ApiResponse(responseCode = "200", description = "Successful retrieval of contacts", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Contact.class))))

    @Operation(summary = "Retrieves contacts", description = "Provides a list of all contacts")

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Contact>> getContacts() {

        return new ResponseEntity<>(contactService.getContacts(), HttpStatus.OK);
    }

    @ApiResponse(responseCode = "404", description = "Failed retrieval of contact. Id not exist")

    @ApiResponse(responseCode = "200", description = "Successful retrieval of contact")

    @Operation(summary = "Retrieves a contact", description = "Returns a contact based on an ID")

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Contact> getById(@PathVariable String id) {
        HttpStatus result = contactService.isIdValid(id);
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, result);

    }

    @ApiResponse(responseCode = "404", description = "Failed retrieval of contact. Id not exist")

    @ApiResponse(responseCode = "201", description = "Successful update of contact")

    @Operation(summary = "Creates a contact", description = "Creates a contact from the provided payload")

    @PostMapping(value = "/contact", produces = "application/json")
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {

        HttpStatus status = contactService.saveContact(contact);

        return new ResponseEntity<Contact>(contactService.getContactById(contact.getId()), status);

    }

    @ApiResponse(responseCode = "404", description = "Failed retrieval of contact. Id not exist")

    @ApiResponse(responseCode = "200", description = "Successful update of contact")

    @Operation(summary = "Updates a contact", description = "Updates an existing contact id and a Contact body")

    @PutMapping(value = "contact/{id}", produces = "application/json")
    public ResponseEntity<Contact> putContact(@Valid @RequestBody Contact contact, @PathVariable String id) {

        HttpStatus status = contactService.updateContact(id, contact);

        return new ResponseEntity<>(contactService.getContactById(id), status);
    }

    @ApiResponse(responseCode = "404", description = "Failed deletion of contact. Id not exist")

    @ApiResponse(responseCode = "204", description = "Successful deletion of contact")

    @Operation(summary = "Deletes a contact", description = "Deletes an existing contact with id")

    @DeleteMapping(value = "delete/{id}", produces = "application/json")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id) {
        HttpStatus status = contactService.deleteContact(id);
        return new ResponseEntity<>(status);
    }
}
