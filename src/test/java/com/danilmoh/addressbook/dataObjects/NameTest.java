package com.danilmoh.addressbook.dataObjects;

import com.danilmoh.addressbook.dataObjects.entryObjects.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameTest {
    Name name;
    String firstName;
    String lastName;

    @BeforeEach
    void setup() {
         this.firstName = "Vasya";
         this.lastName = "Pupkin";
         this.name = new Name(firstName, lastName);
    }

    @Test
    void constructorShouldSetValues() {
        assertTrue(this.name.getInfo().get(0).equals(firstName)
                && this.name.getInfo().get(1).equals(lastName));
    }
/*
    @ParameterizedTest
    @CsvSource({"vasily, pup", "abc, def"})*/
    @Test
    void singleSetterShouldWorkAndShouldReturnValuesInCorrectOrder(/*String first, String last*/) {

        // Given
        String first = "vasily", last = "pup";
        this.name.setInfo(first, last);

        // When
        boolean returnsNames = this.name.getInfo().get(0).equals(first)
                && this.name.getInfo().get(1).equals(last);

        // Then
        assertTrue(returnsNames);
    }

    @ParameterizedTest
    @CsvSource({"vasily", "hi", "Hello world"})
    void singleSetterShouldWorkWithOneArgAndShouldReturnValuesInCorrectOrder(String first) {

        // Given
        this.name.setInfo(first);

        // When
        boolean returnsNames = name.getInfo().get(0).equals(first)
                && name.getInfo().get(1).equals(this.lastName);

        // Then
        assertTrue(returnsNames);
    }

    @ParameterizedTest
    @CsvSource({"vasily, pupkIn", "ghi, jkl"})
    void settersShouldWorkAndShouldReturnValuesInCorrectOrder(String first, String last) {
        // Given
        this.name.setFirstName(first);
        this.name.setLastName(last);

        // When
        boolean returnsNames = name.getInfo().get(0).equals(first)
                && name.getInfo().get(1).equals(last);

        // Then
        assertTrue(returnsNames);
    }

    @ParameterizedTest
    @CsvSource({"vasily, pupkin", "mno, pqr" })
    void gettersShouldWork(String first, String last) {
        // Given
        this.name.setFirstName(first);
        this.name.setInfo(this.name.getFirstName(), last);

        // When
        boolean namesMatch = name.getFirstName().equals(first)
                && name.getLastName().equals(last);

        boolean namesDontMatch = name.getFirstName().equals("vasya") &&
                name.getLastName().equals(last);

        // Then
        assertTrue(namesMatch);
        assertFalse(namesDontMatch);
    }
}
