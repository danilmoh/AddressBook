package com.danilmoh.addressbook.dataObjects;

import java.util.HashMap;
import java.util.Map;

public class AddressBook {
    private final String name;
    private final Map<String, Entry> lastNameMap;
    private final Map<String, Entry> firstNameMap;
    private final Map<Long, Entry> phoneMap;
    private final Map<String, Entry> emailMap;
    private final Map<Address, Entry> addressMap;

    public AddressBook(String name) {
        this.name = name;
        this.lastNameMap = new HashMap<>();
        this.firstNameMap = new HashMap<>();
        this.phoneMap = new HashMap<>();
        this.emailMap = new HashMap<>();
        this.addressMap = new HashMap<>();
    }

    public String getName(){
        return name;
    }

    public void addEntry(String lastName, String firstName, long phone, String email, Address address) {
        Entry entry = new Entry(firstName, lastName, phone, address, email);
        lastNameMap.put(lastName, entry);
        firstNameMap.put(firstName, entry);
        phoneMap.put(phone, entry);
        emailMap.put(email, entry);
        addressMap.put(address, entry);
    }

    public void editEntryLastName(String oldLastName, long phone, String newLastName) {
        if(lastNameMap.get(oldLastName).equals(phoneMap.get(phone))){
            Entry changed = lastNameMap.get(oldLastName);
            changed.setLastName(newLastName);
            lastNameMap.remove(oldLastName);
            lastNameMap.put(newLastName, changed);
        }
    }

    public void editEntryFirstName(String oldFirstName, long phone, String newFirstName) {
        if(firstNameMap.get(oldFirstName).equals(phoneMap.get(phone))){
            Entry changed = firstNameMap.get(oldFirstName);
            changed.setLastName(newFirstName);
            firstNameMap.remove(oldFirstName);
            firstNameMap.put(newFirstName, changed);
        }
    }

    public void editEntryPhoneNumber(String lastName, long phone, long newPhone) {
        if(lastNameMap.get(lastName).equals(phoneMap.get(phone))) {
            Entry changed = phoneMap.get(phone);
            changed.setPhoneNumber(newPhone);
            phoneMap.remove(phone);
            phoneMap.put(newPhone, changed);
        }
    }

    public void editEmail(String oldEmail, long phone, String newEmail) {
        if(emailMap.get(oldEmail).equals(phoneMap.get(phone))) {
            Entry changed = emailMap.get(oldEmail);
            changed.setEmail(newEmail);
            emailMap.remove(oldEmail);
            emailMap.put(newEmail, changed);
        }
    }

    public void editAddress(Address oldAddress, long phone, Address newAddress) {
        if(addressMap.get(oldAddress).equals(phoneMap.get(phone))) {
            Entry changed = addressMap.get(oldAddress);
            changed.setAddress(newAddress);
            addressMap.remove(oldAddress);
            addressMap.put(newAddress, changed);
        }
    }
}
