package com.xbao.dao;

import com.xbao.entity.Contact;

import java.util.ArrayList;
import java.util.Collections;

public class ContactDAOImp implements ContactDAO {
    private FileSvc fs = new FileSvc();
    private ArrayList<Contact> contacts = fs.loadFile();

    public ContactDAOImp(FileSvc fs) {
        this.fs = fs;
    }

    @Override
    public void readContact() {
        Collections.sort(contacts);
        for (Contact contact: contacts){
            System.out.println(contact.toString());
            continue;
        }
    }

    @Override
    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    @Override
    public void save() {
        fs.writeFile();
    }


    @Override
    public boolean isContactListChanged() {
        ArrayList<Contact> listInFile = fs.loadFile();
        if (listInFile.size() == contacts.size()) {
            for (Contact contact1 : listInFile) {
                for (Contact contact2 : contacts) {
                    if(contact1.getFirst_name().equals(contact2.getFirst_name())&&
                        contact1.getLast_name().equals(contact2.getLast_name())&&
                        contact1.getAddress().equals(contact2.getAddress())&&
                        contact1.getZip().equals(contact2.getZip())&&
                        contact1.getPhone_number().equals(contact2.getPhone_number())&&
                        contact1.getEmail().equals(contact2.getEmail())){
                        return false;
                    }
                }
            }
            //return true;
        }
        return true;
    }

    @Override
    public int findContactIndex(String searchEmail) {
        int index = -1;
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(searchEmail)) {
                {
                    return contacts.indexOf(contact);
                }
            }
        }
        return index;
    }

    @Override
    public Contact findContact(int index) {
        System.out.println(contacts.get(index).toString());
        return contacts.get(index);
    }

    @Override
    public Contact updateContact(Contact contact, String phone, String address, String zip, String email) {
        if(!phone.equals("")){
            contact.setPhone_number(phone);
        }
        if(!address.equals("")){
            contact.setAddress(address);
        }
        if(!zip.equals("")){
            contact.setZip(zip);
        }
        if(!email.equals("")){
            contact.setEmail(email);
        }
        return contact;
    }

    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
    }
}

