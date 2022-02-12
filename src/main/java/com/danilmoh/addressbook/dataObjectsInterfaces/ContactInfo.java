package com.danilmoh.addressbook.dataObjectsInterfaces;

import java.util.ArrayList;
import java.util.List;

public abstract class ContactInfo <T>{
    protected final int NUMBEROFELEMENTS;

    protected ContactInfo (int numberOfElements) {
        this.NUMBEROFELEMENTS = numberOfElements;
    }

    protected List<T> info = new ArrayList<>();

    public List<T> getInfo(){
        return this.info;
    }

    public abstract void setInfo(T... objects);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactInfo)) return false;

        ContactInfo<?> that = (ContactInfo<?>) o;

        if (NUMBEROFELEMENTS != that.NUMBEROFELEMENTS) return false;
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        int result = NUMBEROFELEMENTS;
        result = 31 * result + info.hashCode();
        return result;
    }
}
