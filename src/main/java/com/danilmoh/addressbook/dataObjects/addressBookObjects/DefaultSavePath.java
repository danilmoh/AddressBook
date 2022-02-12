package com.danilmoh.addressbook.dataObjects.addressBookObjects;

public enum DefaultSavePath {
    SAVE_PATH(System.getProperty("user.dir")+System.getProperty("file.separator")+
            "AddressBookDefaultSavePath"+System.getProperty("file.separator"));

    private final String path;

    DefaultSavePath(String s) {
        this.path = s;
    }

    public String getPath() {
        return this.path;
    }
}
