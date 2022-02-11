package com.danilmoh.addressbook.dataObjectsInterfaces;

public abstract class UniqueContactInfo<T> extends ContactInfo<T> {
    protected UniqueContactInfo(int numberOfElements) {
        super(numberOfElements);
    }
}
