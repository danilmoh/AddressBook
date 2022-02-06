package com.danilmoh.addressbook.dataReader;

import com.danilmoh.addressbook.dataObjects.AddressBook;

import java.io.FileWriter;
import java.io.IOException;

public class BookWriter {
    public static void saveBook(AddressBook book) {
        try {
            FileWriter writer = new FileWriter(book.getName()+".csv");
            writer.write("Hello world");
            writer.write("\n");
            writer.write("Hello world #2");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
