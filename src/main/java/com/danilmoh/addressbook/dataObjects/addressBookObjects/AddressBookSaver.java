package com.danilmoh.addressbook.dataObjects.addressBookObjects;

import com.danilmoh.addressbook.dataObjects.Entry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.danilmoh.addressbook.services.NonNullArrayRequirer.requireNonNull;

public class AddressBookSaver {
    private final List<AddressBook> books;
    private final String savePath;

    public AddressBookSaver(List<AddressBook> books) {
        requireNonNull(books);
        this.books = books;
        this.savePath = DefaultSavePath.SAVE_PATH.getPath();
    }

    public AddressBookSaver(List<AddressBook> books, String savePathStr) {
        requireNonNull(books);
        requireNonNull(savePathStr);

        File savePathFile = new File(savePathStr);
        if (savePathFile.exists() && savePathFile.isDirectory())
            this.savePath = savePathStr;
        else
            this.savePath = DefaultSavePath.SAVE_PATH.getPath();

        this.books = books;
    }

    public void save() {
        for(AddressBook book : books) {
            String fileName = savePath+book.getName()+".csv";
            try {
                File saveFile = new File(fileName);
                boolean fileCreated = saveFile.createNewFile();
                if(fileCreated) {
                    FileWriter writer = new FileWriter(saveFile);
                    List<Entry> currentEntries = book.getEntries();
                    for(Entry entry : currentEntries) {
                        StringBuilder entryString = new StringBuilder();
                        entryString.append(entry.getName().getFirstName()).append(",").append(entry.getName().getLastName()).append(",")
                                .append(entry.getAddress().getCountry()).append(",").append(entry.getAddress().getRegion()).append(",")
                                .append(entry.getAddress().getCity()).append(",").append(entry.getAddress().getStreet()).append(",")
                                .append(entry.getAddress().getHouse()).append(",").append(entry.getPhoneNumber().getValue()).append(",")
                                .append(entry.getEmail().getValue());
                        writer.write(entryString + "\n");
                    }
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
