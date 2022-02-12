package com.danilmoh.addressbook.dataObjects.entryObjects;

import com.danilmoh.addressbook.dataObjectsInterfaces.UniqueContactInfo;
import com.danilmoh.addressbook.services.NonNullArrayRequirer;

public class PhoneNumber extends UniqueContactInfo<Long> {
    public PhoneNumber(String number) {
        super(1);
        NonNullArrayRequirer.requireNonNull(number);

        super.info.add(Long.parseLong(number));
    }

    public PhoneNumber(Long number) {
        super(1);
        NonNullArrayRequirer.requireNonNull(number);
        super.info.add(number);
    }

    @Override
    public void setInfo(Long... objects) {
        NonNullArrayRequirer.requireNonNull(objects);

        if (objects[0]!=null) super.info.set(0, objects[0]);
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    public void setValue(Long number) {
        NonNullArrayRequirer.requireNonNull(number);

        super.info.set(0, number);
    }

    public void setValue(String  number) {
        NonNullArrayRequirer.requireNonNull(number);

        setValue(Long.parseLong(number));
    }

    public Long getValue() {
        return info.get(0);
    }
}
