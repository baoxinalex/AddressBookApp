package com.xbao.menu;

import com.xbao.db_conn_factory.FileConnFactory;
import com.xbao.entity.Contact;
import com.xbao.services.ContactSvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private FileConnFactory factory = new FileConnFactory();
    private ContactSvc svc = factory.getContactSvc();


    private Contact contact = null;
    private String confirmation = null;

    private String address = null;
    private String zip = null;
    private String phone = null;
    private String email = null;
    private boolean switcher = true;

    public void addressBookApp() {
        String first_name = null;
        String last_name = null;
        String option = null;


        //menu shows up automatically
        loop:
        while (true) {
            System.out.println("------Address Book App-------");
            System.out.println("1. Read all contacts");
            System.out.println("2. Search a contact");
            System.out.println("3. Edit a contact");
            System.out.println("4. Delete a contact");
            System.out.println("5. Add a contact");
            System.out.println("6. Save");
            System.out.println("7. Exit");
            System.out.println("----------------------");
            System.out.println("Please enter number 1-7 to select the operation");

            try {
                option = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (option) {
                case "1":
                    svc.readContact();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    editContact();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    System.out.println("Please enter the following info of the contact:");

                    try {
                        System.out.println("First name");
                        first_name = reader.readLine();
                        if (first_name.equals("")) first_name = "null";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        System.out.println("Last name");
                        last_name = reader.readLine();
                        if (last_name.equals("")) last_name = "null";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        System.out.println("Address");
                        address = reader.readLine();
                        if (address.equals("")) address = "null";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        System.out.println("Zip");
                        zip = reader.readLine();
                        if (zip.equals("")) zip = "null";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        System.out.println("Phone number");
                        phone = reader.readLine();
                        if (phone.equals("")) phone = "null";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        System.out.println("Email");
                        email = reader.readLine();
                        if (email.equals("")) email = "null";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    contact = new Contact(first_name, last_name, address, zip, phone, email);
                    svc.addContact(contact);
                    break;

                case "6":
                    svc.save();
                    break;
                case "7":
                    if (svc.isContactListChanged()) {
                        try {
                            System.out.println("The file has been changed, do you want to save it? yes to save, any other keys to exit without saving.");
                            confirmation = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        svc.exit(confirmation);
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid option number.");
            }
        }
    }

    public Contact searchContact() {
        while (switcher) {
            try {
                System.out.println("Please enter an email");
                email = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            contact = svc.findContact(email);
            if (contact != null) {
                System.out.println("Find the contact.");
                switcher = false;
                return contact;
            } else {
                System.out.println("Can't find any contact with this email. Do you want to continue searching for the contact? yes/no");
                try {
                    confirmation = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (confirmation.toLowerCase()) {
                    case "yes":
                        break;
                    case "no":
                        switcher = false;
                        break;
                    default:
                        System.out.println("Please enter yes or no");
                        break;
                }
            }
        }
        return null;
    }

        private void editContact(){
            contact = searchContact();
            try {
                System.out.println("Please enter a new Address or press enter leaving it empty");
                address = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Please enter a new Zip code or press enter leaving it empty");
                zip = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Please enter a new Phone number or press enter leaving it empty");
                phone = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Please enter a new Email or press enter leaving it empty");
                email = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Contact contact1 = svc.updateContact(contact, phone,address,zip, email);
            System.out.println("Contact is updated:");
            System.out.println(contact1.toString());
        }



        private void deleteContact(){
            searchContact();
            svc.deleteContact(contact);
            System.out.println("This contact is deleted.");
        }

}
