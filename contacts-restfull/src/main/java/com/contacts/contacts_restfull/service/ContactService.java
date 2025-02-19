package com.contacts.contacts_restfull.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.contacts.contacts_restfull.Constants;
import com.contacts.contacts_restfull.Contact;
import com.contacts.contacts_restfull.repository.ContactsRepository;

@Service
public class ContactService {

    @Autowired
    private ContactsRepository contactsRepository;

    public List<Contact> getContacts() {
        return contactsRepository.getContacts();
    }

    public int getIndexById(String id) {
        List<Contact> contactList = contactsRepository.getContacts();

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public int getIndexByContact(Contact contact) {
        List<Contact> contactList = contactsRepository.getContacts();

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getId().equals(contact.getId())) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public int getIndexByName(String name) {
        List<Contact> contactList = contactsRepository.getContacts();

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public Contact getContactById(String id) {
        int index = getIndexById(id);
        return (index == Constants.NOT_FOUND) ? new Contact() : contactsRepository.getContactByIndex(index);
    }

    public boolean isIdValid(String id) {
        int index = getIndexById(id);
        return (index == Constants.NOT_FOUND) ? false : true;
    }

    public boolean isNameValid(String id) {
        int index = getIndexByName(id);
        return (index == Constants.NOT_FOUND) ? false : true;
    }

    public Contact getContactByName(String name) {
        int index = getIndexByName(name);
        return (index == Constants.NOT_FOUND) ? new Contact() : contactsRepository.getContactByIndex(index);
    }

    public HttpStatus saveContact(Contact contact) {
        int index = getIndexById(contact.getId());
        if (index == Constants.NOT_FOUND) {
            contactsRepository.addContact(contact);
            return HttpStatus.CREATED;
        } else {
            contactsRepository.updateContact(index, contact);
            return HttpStatus.OK;
        }
    }

    public HttpStatus updateContact(String id, Contact contact) {

        int index = getIndexById(id);
        if (index != Constants.NOT_FOUND) {
            contact.setId(id);
            contactsRepository.updateContact(index, contact);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus deleteContact(String id) {
        int index = getIndexById(id);
        if (index != Constants.NOT_FOUND) {
            contactsRepository.deleteContact(index);
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.NOT_FOUND;
    }

    public Contact getContactByIndex(int index) {
        return contactsRepository.getContactByIndex(index);
    }

    public boolean isContactBodyValid(Contact contact) {
        return (contact.getName() == null || contact.getPhoneNumber() == null) ? false : true;

    }
}
