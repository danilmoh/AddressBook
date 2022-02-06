package com.danilmoh.addressbook.dataObjects;

public class Address {
    private String country;
    private String province;
    private String city;
    private String street;
    private int houseNumber;
    private int postalIndex;

    public Address(String country, String province, String city, String street, int houseNumber, int postalIndex) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalIndex = postalIndex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getPostalIndex() {
        return postalIndex;
    }

    public void setPostalIndex(int postalIndex) {
        this.postalIndex = postalIndex;
    }
    @Override
    public String toString() {
        return country + ", " + province + ", " + city + ", " + street + ", " + houseNumber + ", " + postalIndex;
    }
}
