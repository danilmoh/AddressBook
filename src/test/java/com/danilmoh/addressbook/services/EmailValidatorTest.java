package com.danilmoh.addressbook.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    @ParameterizedTest
    @CsvSource({"example.show@list.ru", "foobar.mail@gmail.com", "england.ui@mail.uk"})
    void shouldBeValidEmail(String email) {
        assertTrue(EmailValidator.isValidEmail(email));
    }

    @ParameterizedTest
    @CsvSource({"abrakadabra@gmail.com","holivoodmovie@@.us", "foo@bar", "foobar"})
    void shouldBeInvalidEmail(String email) {
        assertFalse(EmailValidator.isValidEmail(email));
    }
}
