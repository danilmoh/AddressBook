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



    public String getFirstName() {
        return this.name.getFirstName();
    }

    public String getLastName() {
        return this.name.getLastName();
    }

    public String getCountry() {
        return this.address.getCountry();
    }

    public String getRegion() {
        return this.address.getRegion();
    }

    public String getCity() {
        return this.address.getCity();
    }

    public String getStreet() {
        return this.address.getStreet();
    }

    public String getHouse() {
        return this.address.getHouse();
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

    public String getPhoneNumber() {
        return this.phoneNumber.toString();
    }

    public void setPhoneNumber(String value) {
        this.phoneNumber.setValue(value);
    }

    public String getEmail() {
        return this.email.toString();
    }

    public void setEmail(String value) {
        this.email.setValue(value);
    }

    public PhoneNumber getPhoneNumberObj() {
        return phoneNumber;
    }

    public void setPhoneNumberObj(PhoneNumber phoneNumber) {
        requireNonNull(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public Email getEmailObj() {
        return email;
    }

    public void setEmailObj(Email email) {
        requireNonNull(email);
        this.email = email;
    }

    @Override
    public String toString() {
        return this.name.toString() + ":\n" + this.address.toString() + ";\n" + this.phoneNumber.toString() + ";\n" + this.email.toString();
    }
}
