package com.xbao.dao;

import com.xbao.entity.Contact;

public interface ContactDAO {

    void readContact();
    int findContactIndex(String email);
    Contact findContact(int index);
    Contact updateContact(Contact contact, String phone, String address, String zip, String email);
    void addContact(Contact contact);
    void deleteContact(Contact contact);
    void save();
    boolean isContactListChanged();
}
