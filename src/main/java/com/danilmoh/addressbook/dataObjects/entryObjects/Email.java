package com.danilmoh.addressbook.dataObjects.entryObjects;

import com.danilmoh.addressbook.dataObjectsInterfaces.UniqueContactInfo;
import com.danilmoh.addressbook.services.EmailValidator;
import com.danilmoh.addressbook.services.NonNullArrayRequirer;

import static java.util.Objects.requireNonNull;

public class Email extends UniqueContactInfo<String> {

    public Email(String email) {
        super(1);
        NonNullArrayRequirer.requireNonNull(email);
        super.info.set(0, email);
    }

    @Override
    public void setInfo(String... objects) {
        NonNullArrayRequirer.requireNonNull(objects);

        if(objects[0]!=null && EmailValidator.isValidEmail(objects[0])) {
            super.info.set(0, objects[0]);
        }
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public void setValue(String email) {
        NonNullArrayRequirer.requireNonNull(email);

        if(EmailValidator.isValidEmail(email)) {
            super.info.set(0, email);
        }
    }

    public String getValue(){
        return super.info.get(0);
    }
}
