package com.danilmoh.addressbook.dataObjects;

import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressTest {
    private Address underTest;

    @BeforeEach
    void setUp() {

    }

    @DisplayName("Should pass when every value is returned")
    void shouldReturnAllValuesWhenConstructorIsCalled() {
        // Given
        String country = "ABC", region = "PQRegion", city = "ABCity", street = "street1", house="12 building #1";

        // When
        underTest = new Address(country, region, city, street, house);
        boolean allValues = underTest.getInfo().containsAll(Arrays.asList(country, region, city, street, house));
        // Then
        assertTrue(allValues);
    }

    @Test
    @DisplayName("Should pass when every object value is returned from setter")
    void shouldReturnValuesWhenSettersAreCalled() {
        // Given
        String country = "ABC", region = "PQRegion", city = "ABCity", street = "street1", house="12 building #1";
        String country2 = "BCD", region2 = "SomeRegion", city2 = "SomeCity", street2 = "SomeStreet", house2 = "50, building #8";

        // When
        underTest = new Address(country, region, city, street, house);
        underTest.setCountry(country2);
        underTest.setRegion(region2);
        underTest.setCity(city2);
        underTest.setStreet(street2);
        underTest.setHouse(house2);
        boolean allValues = underTest.getInfo().containsAll(Arrays.asList(country2, region2, city2, street2, house2));
        // Then
        assertTrue(allValues);
    }

    @Test
    @DisplayName("Should return all values from getters when inputting from constructor")
    void shouldReturnAllConstructorInputValuesFromGetters() {
        // Given
        String country = "ABC", region = "PQRegion", city = "ABCity", street = "street1", house="12 building #1";

        // When
        underTest = new Address(country, region, city, street, house);
        boolean allValues = underTest.getCountry().equals(country) && underTest.getRegion().equals(region) &&
                underTest.getCity().equals(city) && underTest.getStreet().equals(street) && underTest.getHouse().equals(house);
        // Then
        assertTrue(allValues);
    }

    @Test
    @DisplayName("Should return all values from list in the correct order when inputting from constructor")
    void shouldReturnAllConstructorInputValuesFromListByTheOrder() {
        // Given
        String country = "ABC", region = "PQRegion", city = "ABCity", street = "street1", house="12 building #1";

        // When
        underTest = new Address(country, region, city, street, house);
        List<String> info = underTest.getInfo();
        boolean allValues = info.get(0).equals(country) && info.get(1).equals(region) &&
                info.get(2).equals(city) && info.get(3).equals(street) && info.get(4).equals(house);
        // Then
        assertTrue(allValues);
    }

    @Test
    @DisplayName("Should return all values from getters when inputting from setters")
    void shouldReturnAllSetterInputValuesFromListByTheOrder() {
        // Given
        String country = "ABC", region = "PQRegion", city = "ABCity", street = "street1", house="12 building #1";
        String country2 = "BCD", region2 = "SomeRegion", city2 = "SomeCity", street2 = "SomeStreet", house2 = "50, building #8";

        // When
        underTest = new Address(country, region, city, street, house);
        underTest.setCountry(country2);
        underTest.setRegion(region2);
        underTest.setCity(city2);
        underTest.setStreet(street2);
        underTest.setHouse(house2);
        List<String> info = underTest.getInfo();
        boolean allValues = info.get(0).equals(country2) && info.get(1).equals(region2) &&
                info.get(2).equals(city2) && info.get(3).equals(street2) && info.get(4).equals(house2);
        // Then
        assertTrue(allValues);
    }

    @Test
    @DisplayName("Should return all values from getters when inputting from single setter")
    void singleSetterShouldWork() {
        // Given
        String country = "ABC", region = "PQRegion", city = "ABCity", street = "street1", house="12 building #1";
        String country2 = "BCD", region2 = "SomeRegion", city2 = "SomeCity", street2 = "SomeStreet", house2 = "50, building #8";

        // When
        underTest = new Address(country, region, city, street, house);
        underTest.setInfo(country2, region2, city2, street2, house2);
        List<String> info = underTest.getInfo();
        boolean allValues = info.get(0).equals(country2) && info.get(1).equals(region2) &&
                info.get(2).equals(city2) && info.get(3).equals(street2) && info.get(4).equals(house2);
        // Then
        assertTrue(allValues);
    }

}
