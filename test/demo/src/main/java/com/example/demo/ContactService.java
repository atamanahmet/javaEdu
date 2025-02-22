package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;

    public Contact getContact(String Id) {
        int index = getIndexById(Id);
        if (index != Constants.NOT_FOUND) {
            return repository.getContactByIndex(index);
        }
        throw new IllegalArgumentException();
    }

    public int getIndexById(String id) {
        List<Contact> contactList = repository.getList();
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;

    }

    public List<Contact> getContacts() {
        return repository.getList();
    }

    public void saveContact(Contact contact) {
        int index = getIndexById(contact.getId());
        if (index == Constants.NOT_FOUND) {
            repository.add(contact);
        } else {
            repository.updateContact(index, contact);
        }
    }
}
