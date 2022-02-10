package com.danilmoh.addressbook.dataObjectsInterfaces;

import java.util.*;

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

}
