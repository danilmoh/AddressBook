package com.danilmoh.addressbook.dataObjects.entryObjects;

import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {
    Email email;
    String val = "example@gmail.com";

    @BeforeEach
    void setUp(){
        this.email = new Email(val);
    }

    @Test
    void setInfoAddsOnlyValidAddresses(String address) {
        // Given
        String invalidAddress = "foobar@foo";
        String validAddress = "example@gmail.ru";
        Email e1 = new Email("helloworld@gmail.com");
        e1.setInfo(val);
        e1.setInfo(address);
        Email e2 = new Email(validAddress);
        e2.setInfo(validAddress);

        // When
        boolean didNotSet = e1.getValue().equals(val);
        boolean setNewAddress = e2.getValue().equals(validAddress);

        // Then
        assertTrue(didNotSet);
        assertFalse(setNewAddress);
    }

    @Test
    void setterAddsOnlyValidAddresses(String address) {
        // Given
        String invalidAddress = "foobar@foo";
        String validAddress = "example@gmail.com";
        Email e1 = new Email(val);
        e1.setValue(val);
        e1.setValue(invalidAddress);
        Email e2 = new Email(validAddress);
        e2.setValue(validAddress);

        // When
        boolean didNotSet = e1.getValue().equals(val);
        boolean setNewAddress = e2.getValue().equals(validAddress);

        // Then
        assertTrue(didNotSet);
        assertFalse(setNewAddress);
    }

    @Test
    void constructorAddsOnlyValidAddresses() {
        // Given
        String invalidAddress = "foobar@foo";
        String validAddress = "example@gmail.com";
        Email e1 = new Email(invalidAddress);
        Email e2 = new Email(validAddress);

        // When
        boolean setInvalid = e1.getValue().equals(invalidAddress);
        boolean setValid = e2.getValue().equals(validAddress);

        // Then
        assertFalse(setInvalid);
        assertTrue(setValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"foobar@foo", "example@gmail.com"})
    void getterShouldWork(String address) {
        email.setValue(address);
        assertEquals(email.getValue(), address);
    }

    @ParameterizedTest
    @ValueSource(strings = {"foo.bar@gmail.ru", "hello@list.ru"})
    void toStringMethodWorks(String address) {
        Email mail = new Email(address);
        assertEquals(mail.toString(), address);
    }

}
