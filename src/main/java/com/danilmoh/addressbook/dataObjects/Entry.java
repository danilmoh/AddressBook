package com.danilmoh.addressbook.dataObjects;

import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import com.danilmoh.addressbook.dataObjects.entryObjects.Name;
import com.danilmoh.addressbook.dataObjects.entryObjects.PhoneNumber;

import static com.danilmoh.addressbook.services.NonNullArrayRequirer.requireNonNull;

public class Entry {
    private Name name;
    private Address address;
    private PhoneNumber phoneNumber;
    private Email email;

    public Entry(Name name, Address address, PhoneNumber number, Email email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = number;
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        requireNonNull(address);
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        requireNonNull(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        requireNonNull(email);
        this.email = email;
    }

    @Override
    public String toString() {
        return this.name.toString() + ":\n" + this.address.toString() + ";\n" + this.phoneNumber.toString() + ";\n" + this.email.toString();
    }
}
