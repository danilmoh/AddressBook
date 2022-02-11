package com.danilmoh.addressbook.dataObjects.addressBookObjects;

import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import com.danilmoh.addressbook.dataObjects.entryObjects.Name;
import com.danilmoh.addressbook.dataObjects.entryObjects.PhoneNumber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressBookTest {

    AddressBook addressBook;

    List<Entry> entries = new ArrayList<>();

    Entry entry9 = new Entry(
            new Name("firstName"+9,"lastName"+9),
            new Address("country"+9,"region"+9,"city"+9,"street"+9,"house"+9),
            new PhoneNumber((9+"0000")),
            new Email("example.foo9@mail.com")
    );

    void defaultAddressBook() {
        this.addressBook = new AddressBook("address book");
        entries.clear();
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

        assertEquals(addressBook.getEntries().get(addressBook.getEntries().size()-1), entry9);
    }

    @Test
    void testAddressBookGetters() {
        assertTrue(addressBook.getEntryByLastName("lastName0").equals(addressBook.getEntryByLastName("firstName0"))&&
                addressBook.getEntryByPhoneNumber(new PhoneNumber("00000")).equals(addressBook.getEntryByEmailAddress(new Email("example.foo0@mail.com")))&&
                addressBook.getEntryByAddress(new Address("country"+0,"region"+0,"city"+0,"street"+0,"house"+0)).equals(
                        addressBook.getEntryByEmailAddress(new Email("example.foo0@mail.com")))
        );
    }
}
