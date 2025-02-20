package com.contacts.contacts_restfull.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.contacts.contacts_restfull.Contact;

@Repository
public class ContactsRepository {
    private List<Contact> contactList = new ArrayList<>();

    public ContactsRepository() {
        contactList.add(new Contact("id1", "a", "1"));
        contactList.add(new Contact("id2", "b", "2"));
    }

    public List<Contact> getContacts() {
        return this.contactList;
    }

    public Contact getContactByIndex(int index) {
        return contactList.get(index);
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    public void updateContact(int index, Contact contact) {
        contactList.set(index, contact);
    }

    public void deleteContact(int index) {
        contactList.remove(index);

    }

}
