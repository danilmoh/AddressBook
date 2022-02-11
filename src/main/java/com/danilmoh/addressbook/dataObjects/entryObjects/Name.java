package com.danilmoh.addressbook.dataObjects.entryObjects;

import com.danilmoh.addressbook.dataObjectsInterfaces.ContactInfo;
import com.danilmoh.addressbook.services.NonNullArrayRequirer;

import java.util.Arrays;
import static java.util.Objects.requireNonNull;

public class Name extends ContactInfo<String> {

    // list: firstName(0), lastName(1)
    public Name(String firstName, String lastName) {
        super(2);
        NonNullArrayRequirer.requireNonNull(firstName, lastName);
        super.info.add(firstName);
        super.info.add(lastName);
    }

    @Override
    public void setInfo(String... strings) {
        NonNullArrayRequirer.requireNonNull(strings);
        if (strings.length == super.NUMBEROFELEMENTS) {
            super.info.clear();
            super.info.addAll(Arrays.asList(strings));
        } else if(strings.length == 1){
            super.info.set(0, strings[0]);
        }
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public void setFirstName(String firstName) {
        NonNullArrayRequirer.requireNonNull(firstName);
        super.info.set(0, firstName);
    }

    public void setLastName(String lastName) {
        NonNullArrayRequirer.requireNonNull(lastName);
        requireNonNull(lastName);
        super.info.set(1, lastName);
    }

    public String getFirstName() {
        return info.get(0);
    }

    public String getLastName() {
        return info.get(1);
    }
}
