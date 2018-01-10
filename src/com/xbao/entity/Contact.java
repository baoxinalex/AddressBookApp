package com.xbao.entity;

import java.util.ArrayList;

public class Contact implements Comparable<Contact> {


    private  ArrayList<Contact> contacts = new ArrayList<>();

    private String first_name;
    private String last_name;
    private String address;
    private String phone;
    private String email;
    private String zip;

    public Contact(String first_name, String last_name, String address, String zip, String phone, String email) {
        this.first_name = first_name;
        this.last_name=last_name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.zip = zip;
    }


    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone;
    }

    public void setPhone_number(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    //sort by zip code
    @Override
    public int compareTo(Contact o) {
        return zip.compareToIgnoreCase(o.zip);
    }

    @Override
    public String toString(){
        return "Name: "+getFirst_name()+" "+getLast_name()+ "      Address: "+getAddress() + "       Zip: "+getZip()+ "       Phone: " + getPhone_number() + "      Email: "+ getEmail()+"\n";
    }
}
