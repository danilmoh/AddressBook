package com.danilmoh.addressbook.dataObjects;

import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import com.danilmoh.addressbook.dataObjects.entryObjects.Name;
import com.danilmoh.addressbook.dataObjects.entryObjects.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {

    Entry entry;

    Name name = new Name("Vasya", "Pupkin");
    Address address = new Address("country", "region", "city", "street", "house");
    PhoneNumber number = new PhoneNumber("+12345678");
    Email email = new Email("helloworld.foo@example.com");

    void defaultEntry() {
        entry = new Entry(name, address, number, email);
    }

    @Test
    void testNameSetterAndGetter() {
        Name nullName = null;
        Name newName = new Name("hello", "world");
        defaultEntry();
        Exception exception = assertThrows(NullPointerException.class, () -> entry.setName(nullName));

        assertEquals(entry.getName(), name);

        entry.setName(newName);

        assertEquals(entry.getName(), newName);
    }

    @Test
    void testAddressSetterAndGetter() {
        Address nullAddress = null;
        Address newAddress = new Address("hello", "world", "wello", "horld","home");
        defaultEntry();
        Exception exception = assertThrows(NullPointerException.class, () -> entry.setAddress(nullAddress));

        assertEquals(entry.getAddress(), address);

        entry.setAddress(newAddress);

        assertEquals(entry.getAddress(), newAddress);
    }

    @Test
    void testPhoneNumberSetterAndGetter() {
        PhoneNumber nullNumber = null;
        PhoneNumber newNumber = new PhoneNumber("+987654321");
        defaultEntry();
        Exception exception = assertThrows(NullPointerException.class, () -> entry.setPhoneNumberObj(nullNumber));

        assertEquals(entry.getPhoneNumberObj(), number);

        entry.setPhoneNumberObj(newNumber);

        assertEquals(entry.getPhoneNumberObj(), newNumber);
    }

    @Test
    void testEmailSetterAndGetter() {
        Email nullEmail = null;
        Email newEmail = new Email("new.email@example.com");
        defaultEntry();
        Exception exception = assertThrows(NullPointerException.class, () -> entry.setEmailObj(nullEmail));

        assertEquals(entry.getEmailObj(), email);

        entry.setEmailObj(newEmail);

        assertEquals(entry.getEmailObj(), newEmail);
    }

    @Test
    void testToString() {
        defaultEntry();

        assertTrue(entry.toString().contains(name.toString()) &&
                entry.toString().contains(address.toString()) &&
                entry.toString().contains(number.toString()) &&
                entry.toString().contains(email.toString()));
    }
}
