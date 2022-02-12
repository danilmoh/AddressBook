package com.danilmoh.addressbook.dataObjects.entryObjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneNumberTest {

    Long val;

    @BeforeEach
    void setup() {
        this.val = 3216547698L;
    }

    @Test
    void getInfoWorksAndConstructorLongValueAssignmentWorks() {
        // Given
        PhoneNumber number = new PhoneNumber(val);

        // Then
        assertEquals(number.getInfo().get(0).toString(), val.toString());
    }

    @Test
    void getInfoWorksAndConstructorStringValueAssignmentWorks() {
        // Given
        String val = this.val.toString();
        PhoneNumber number = new PhoneNumber(val);

        // Then
        assertEquals(number.getInfo().get(0).toString(), val);
    }

    @Test
    void setInfoWorks() {
        PhoneNumber number = new PhoneNumber(val);
        Long newVal = 6549870909L;
        number.setInfo(newVal);

        assertEquals(number.getInfo().get(0).toString(), newVal.toString());
    }

    @Test
    void setterWorksWithConstructorValueAssignment() {
        // Given
        Long val = 3216547698L;
        Long newVal = 1234567890L;
        PhoneNumber number = new PhoneNumber(val);
        number.setValue(newVal);

        // Then
        assertEquals(number.getInfo().get(0), newVal);
    }

    @Test
    void gettersWorkWithLongConstructorValueAssignment() {
        // Given
        PhoneNumber number = new PhoneNumber(val);

        // Then
        assertEquals(number.getValue(), val);
    }

    @Test
    void gettersWorkWithStringConstructorValueAssignment() {
        // Given
        PhoneNumber number = new PhoneNumber(val.toString());

        // Then
        assertEquals(number.getValue(), val);
    }

    @Test
    void toStringShouldWork() {
        PhoneNumber number = new PhoneNumber(val);
        assertEquals(number.toString(), number.getValue().toString());
    }
}
