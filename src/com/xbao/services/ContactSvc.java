package com.xbao.services;

import com.xbao.dao.ContactDAO;
import com.xbao.entity.Contact;



public class ContactSvc {
    private ContactDAO contactDAO;
    public ContactSvc(ContactDAO dao){
        this.contactDAO = dao;
    }

    private static final String CONFIRM = "yes";
    private static final String REJECT = "no";



    public void readContact(){
        contactDAO.readContact();
    }

    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    public Contact findContact(String email){
        int index = contactDAO.findContactIndex(email);
        if (index!= -1) {
            return contactDAO.findContact(index);
        }
        return null;
    }

    public Contact updateContact(Contact contact, String phone, String address, String zip, String email){
           return contactDAO.updateContact(contact, phone, address, zip, email);
    }

    public void deleteContact(Contact contact){
        contactDAO.deleteContact(contact);
    }

    public void save(){
        contactDAO.save();
    }

    public boolean isContactListChanged(){
        return contactDAO.isContactListChanged();
    }

    public void exit(String confirmation){
        if(confirmation.equalsIgnoreCase(CONFIRM)) {
            contactDAO.save();
            System.exit(0);
        }
        else if (confirmation.equalsIgnoreCase(REJECT))
            System.exit(0);

        System.exit(0);
    }

}
