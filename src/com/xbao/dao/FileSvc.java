package com.xbao.dao;

import com.xbao.entity.Contact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSvc {

    private String commonLocation = System.getProperty("user.dir");
    private String fileLocation = commonLocation+"\\src\\com\\xbao\\resource\\contactsTest.txt";
    private File file= new File(fileLocation);
    private ArrayList<Contact> contacts;

    public ArrayList<Contact> loadFile() {

        contacts = new ArrayList<>();
        if(!file.exists() || !file.canRead() || !file.isFile()){
            System.out.println("Unable to find readable file: "+file.getAbsolutePath());
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line=br.readLine()) != null){
                String[] field = line.split(",");

                String first_name = field[0];
                String last_name = field[1];
                String address = field[2];
                String zip = field[3];
                String phone = field[4];
                String email = field[5];

                contacts.add( new Contact(first_name,last_name,address,zip,phone,email));
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    public void writeFile(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            for (Contact contact: contacts){
                bw.write(contact.getFirst_name()+","+contact.getLast_name()+","+contact.getAddress()+","+contact.getZip()+","+contact.getPhone_number()+","+contact.getEmail());
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
