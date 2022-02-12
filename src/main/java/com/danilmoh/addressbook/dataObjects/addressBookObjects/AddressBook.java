package com.danilmoh.addressbook.dataObjects.addressBookObjects;

import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import com.danilmoh.addressbook.dataObjects.entryObjects.PhoneNumber;

import java.util.*;

import static com.danilmoh.addressbook.services.NonNullArrayRequirer.requireNonNull;

public class AddressBook {
    private final String name;
    private final List<Entry> entries;
    private final Set<Entry> entrySet;

    private final Map<String, Entry> lastNameMap;
    private final Map<Address, Entry> addressMap;
    private final Map<PhoneNumber, Entry> phoneNumberMap;
    private final Map<Email, Entry> emailMap;

    public AddressBook(String name) {
        this.name = name;
        this.entries = new ArrayList<>();
        this.entrySet = new HashSet<>();
        this.lastNameMap = new HashMap<>();
        this.addressMap = new HashMap<>();
        this.phoneNumberMap=new HashMap<>();
        this.emailMap = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public void addEntry(Entry entry) {
        requireNonNull(entry);
        if (!this.entrySet.contains(entry)) {
            this.entrySet.add(entry);
            this.entries.add(entry);
            this.lastNameMap.put(entry.getName().getLastName(), entry);
            this.addressMap.put(entry.getAddress(), entry);
            this.phoneNumberMap.put(entry.getPhoneNumber(), entry);
            this.emailMap.put(entry.getEmail(), entry);
        }
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public Entry getEntryByLastName(String lastName) {
        requireNonNull(lastName);
        return lastNameMap.get(lastName);
    }

    public Entry getEntryByPhoneNumber(PhoneNumber number){
        requireNonNull(number);
        return phoneNumberMap.get(number);
    }

    public Entry getEntryByAddress(Address address) {
        requireNonNull(address);
        return addressMap.get(address);
    }

    public Entry getEntryByEmailAddress(Email email) {
        requireNonNull(email);
        return emailMap.get(email);
    }
}
