package com.danilmoh.addressbook.dataObjects.addressBookObjects;

public enum LastSavePath {
    LAST_SAVE_PATH(System.getProperty("user.dir")+System.getProperty("file.separator")+
            "lastPath.csv");

    private final String path;

    LastSavePath(String s) {
        this.path = s;
    }

    public String getPath() {
        return this.path;
    }
}
