package com.danilmoh.addressbook.dataObjects;

import com.danilmoh.addressbook.dataObjects.dataObjects.Address;
import com.danilmoh.addressbook.dataObjects.dataObjects.AddressBook;
import com.danilmoh.addressbook.dataObjects.dataReader.BookWriter;

public class Main {

    // requirements:
    // entries
    // - [x] first name, last name, phone number, address, email
    // - [ ] should be sorted by any field
    // - [ ] fixed limit of entries, light coded
    // - [ ] displaying entries matching a certain criteria
    // - [ ] handling multiple address books
    // - [ ] moving entry from one address book to another

    // additional goals:
    // learn uml
    // learn junit5
    // learn how to make big programs that handle files

    public static void main(String[] args) {
        AddressBook book = new AddressBook("Saratov address book" );
        Address address = new Address("Russia","Saratovskaya oblast", "Saratov", "Tulupnaya", 8, 123);
        book.addEntry("Mokhonko","Daniil",  89198264815L, "daniil.shmel@list.ru", address);
        book.editEntryLastName("Mokhonko", 89198264815L, "Moh");
        BookWriter.saveBook(book);
    }
}
