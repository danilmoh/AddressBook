package com.danilmoh.addressbook.dataObjects.addressBookObjects;

import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import com.danilmoh.addressbook.dataObjects.entryObjects.Name;
import com.danilmoh.addressbook.dataObjects.entryObjects.PhoneNumber;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.danilmoh.addressbook.services.NonNullArrayRequirer.requireNonNull;

public class AddressBookFetcher {
    private final String fetchPath;
    private final List<AddressBook> books;

    public AddressBookFetcher() {
        this(DefaultSavePath.SAVE_PATH.getPath());
    }

    public AddressBookFetcher(String pathStr) {
        requireNonNull(pathStr);

        File fetchDir = new File(pathStr);
        if (fetchDir.exists() && fetchDir.isDirectory())
            this.fetchPath = pathStr;
        else
            this.fetchPath = DefaultSavePath.SAVE_PATH.getPath();

        this.books = new ArrayList<>();
    }

    public List<AddressBook> fetch() {
        File[] files = new File(fetchPath).listFiles();
        assert files != null;
        Arrays.sort(files);
        if (files.length > 0) {
            for(File file : files) {
                if(file.isFile() && file.toPath().toString().endsWith(".csv") && file.exists()) {
                    AddressBook currentBook = new AddressBook(file.getName().replaceFirst("[.][^.]+$", ""));
                    try(Scanner scanner = new Scanner(file.toPath())) {
                        while(scanner.hasNextLine()) {
                            String[] data = scanner.nextLine().split(",");
                            Entry currentEntry = new Entry(
                                    new Name(data[0], data[1]),
                                    new Address(data[2], data[3], data[4], data[5], data[6]),
                                    new PhoneNumber(data[7]),
                                    new Email(data[8])
                            );
                            currentBook.addEntry(currentEntry);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.books.add(currentBook);
                }
            }
        }
        return this.books;
    }

}
