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
            String fileName = savePath +
                    (savePath.endsWith(System.getProperty("file.separator")) ? "" : System.getProperty("file.separator")) +
                    book.getName()+".csv";
            try {
                File saveFile = new File(fileName);
                if (!saveFile.exists()){
                    saveFile.createNewFile();
                }
                FileWriter writer = new FileWriter(saveFile);
                List<Entry> currentEntries = book.getEntries();
                for(Entry entry : currentEntries) {
                    String entryString = entry.getName().getFirstName() + "," + entry.getName().getLastName() + "," +
                            entry.getAddress().getCountry() + "," + entry.getAddress().getRegion() + "," +
                            entry.getAddress().getCity() + "," + entry.getAddress().getStreet() + "," +
                            entry.getAddress().getHouse() + "," + entry.getPhoneNumberObj().getValue() + "," +
                            entry.getEmailObj().getValue();
                    writer.write(entryString + "\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
