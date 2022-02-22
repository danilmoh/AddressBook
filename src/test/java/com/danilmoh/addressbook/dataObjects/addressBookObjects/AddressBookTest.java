package com.danilmoh.addressbook.dataObjects.addressBookObjects;

import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import com.danilmoh.addressbook.dataObjects.entryObjects.Name;
import com.danilmoh.addressbook.dataObjects.entryObjects.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookTest {

    AddressBook addressBook;

    List<Entry> entries;

    @BeforeEach
    void setup() {
        this.addressBook = new AddressBook("address book");
        this.entries = new ArrayList<>();
    }

    Entry entry9 = new Entry(
            new Name("firstName"+9,"lastName"+9),
            new Address("country"+9,"region"+9,"city"+9,"street"+9,"house"+9),
            new PhoneNumber((9+"0000")),
            new Email("example.foo9@mail.com")
    );

    void defaultAddressBook() {
        entries.clear();
//        addressBook.getEntries().clear();
        addressBook = new AddressBook("address book");
        for(int i = 0; i < 10; i++){
            Entry currentEntry = new Entry(
                    new Name("firstName"+i,"lastName"+i),
                    new Address("country"+i,"region"+i,"city"+i,"street"+i,"house"+i),
                    new PhoneNumber((i+"0000")),
                    new Email("example.foo"+i+"@mail.com")
            );
            entries.add(currentEntry);
            addressBook.addEntry(currentEntry);
        }
    }

    @Test
    void testAddressBookGetEntriesAndSetters() {
        defaultAddressBook();

        assertEquals(addressBook.getEntries().get(addressBook.getEntries().size()-1).toString(), entry9.toString());
    }

    @Test
    void testAddressBookGetters() {
        defaultAddressBook();
        assertEquals(addressBook.getEntryByLastName("lastName0"),addressBook.getEntryByEmailAddress(new Email("example.foo0@mail.com")));
        assertEquals(addressBook.getEntryByPhoneNumber(new PhoneNumber("00000")), addressBook.getEntryByEmailAddress(new Email("example.foo0@mail.com")));
        assertEquals(addressBook.getEntryByAddress(new Address("country"+0,"region"+0,"city"+0,"street"+0,"house"+0)),
                addressBook.getEntryByEmailAddress(new Email("example.foo0@mail.com")));
    }

    @Test
    void testAddressBookToString() {
        assertEquals(entry9.toString(),
                entry9.getName().getFirstName() + " " + entry9.getName().getLastName()+":\n"+
                entry9.getAddress().toString()+";\n"+
                entry9.getPhoneNumberObj().toString()+";\n"+
                entry9.getEmailObj().toString());
    }
}
