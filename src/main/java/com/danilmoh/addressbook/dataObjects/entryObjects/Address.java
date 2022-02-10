package com.danilmoh.addressbook.dataObjects.entryObjects;

import com.danilmoh.addressbook.dataObjectsInterfaces.ContactInfo;
import com.danilmoh.addressbook.services.NonNullArrayRequirer;

import java.util.Arrays;

public class Address extends ContactInfo<String> {

    // list: country(0), region(1), city(2), street(3), houseNumber(4)

    public Address(String country, String region, String city, String street, String house) {
        super(5);
        NonNullArrayRequirer.requireNonNull(country, region, city, street, house);
        super.info.addAll(Arrays.asList(country, region, city, street, house));
    }

    @Override
    public void setInfo(String... strings) {
        NonNullArrayRequirer.requireNonNull(strings);

        if(strings.length == super.NUMBEROFELEMENTS) {
            super.info.clear();
            super.info.addAll(Arrays.asList(strings));
        }
    }

    @Override
    public String toString() {
        return getCity() + ", " + getRegion() + ", " + getCity() +
                ", " + getStreet() + ", "+ getHouse();
    }

    public void setCountry(String country) {
        NonNullArrayRequirer.requireNonNull(country);
        super.info.set(0, country);
    }

    public void setRegion(String region) {
        NonNullArrayRequirer.requireNonNull(region);
        super.info.set(1, region);
    }

    public void setCity(String city) {
        NonNullArrayRequirer.requireNonNull(city);
        super.info.set(2, city);
    }

    public void setStreet(String street) {
        NonNullArrayRequirer.requireNonNull(street);
        super.info.set(3, street);
    }

    public void setHouse(String house) {
        NonNullArrayRequirer.requireNonNull(house);
        super.info.set(4, house);
    }

    public String getCountry() {
        return super.info.get(0);
    }

    public String getRegion() {
        return super.info.get(1);
    }

    public String getCity() {
        return super.info.get(2);
    }

    public String getStreet() {
        return super.info.get(3);
    }

    public String getHouse() {
        return super.info.get(4);
    }
}
