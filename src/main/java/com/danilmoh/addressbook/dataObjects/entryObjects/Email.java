package com.danilmoh.addressbook.dataObjects.entryObjects;

import com.danilmoh.addressbook.dataObjectsInterfaces.UniqueContactInfo;
import com.danilmoh.addressbook.services.EmailValidator;
import com.danilmoh.addressbook.services.NonNullArrayRequirer;

public class Email extends UniqueContactInfo<String> {

    public Email(String email) {
        super(1);
        NonNullArrayRequirer.requireNonNull(email);
        if (EmailValidator.isValidEmail(email))
            super.info.add(email);
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    @Override
    public void setInfo(String... objects) {
        NonNullArrayRequirer.requireNonNull(objects);

        if(objects[0]!=null && EmailValidator.isValidEmail(objects[0])) {
            super.info.clear();
            super.info.add(objects[0]);
        }
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setValue(String email) {
        NonNullArrayRequirer.requireNonNull(email);

        if(EmailValidator.isValidEmail(email)) {
            super.info.set(0, email);
        }
    }

    public String getValue(){
        if (!info.isEmpty())
            return super.info.get(0);
        else
            return "foobar@foo.com";
    }
}
