package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    List<Contact> contactList = new ArrayList<>();

    public Contact getContactByIndex(int index) {
        return contactList.get(index);
    }

    public List<Contact> getList() {
        return this.contactList;
    }

    public void add(Contact contact) {
        contactList.add(contact);
    }

    public void updateContact(int index, Contact contact) {
        contactList.set(index, contact);
    }
}
