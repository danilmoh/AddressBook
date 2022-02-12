package com.danilmoh.addressbook.dataObjects.addressBookObjects;

import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import com.danilmoh.addressbook.dataObjects.entryObjects.Name;
import com.danilmoh.addressbook.dataObjects.entryObjects.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressBookSaverTest {

    String defaultSavePath = DefaultSavePath.SAVE_PATH.getPath();

    AddressBookSaver saver ;
    List<AddressBook> addressBooks;

    @BeforeEach
    void setup() {
        this.addressBooks = new ArrayList<>();
        defaultAddressBooks();
        this.saver = new AddressBookSaver(addressBooks);
    }

    void defaultAddressBooks() {
        addressBooks.clear();
        for(int i = 0; i < 3; i++) {

            AddressBook current = new AddressBook("new"+i);
            for (int j = 0; j < 3; j++) {
                String idStr = "("+i+";"+j+")";
                current.addEntry(new Entry(
                        new Name("first"+idStr, "last"+idStr),
                        new Address("country"+idStr, "region"+idStr,"city"+idStr,"street"+idStr,"house"+idStr),
                        new PhoneNumber("+1234567"+i+j),
                        new Email("example"+i+j+"@gmail.com")
                ));
            }
            addressBooks.add(current);
        }
    }

    @Test
    void shouldSaveThreeFilesIntoDefaultFolder() {
        saver.save();
        String name = "new"+0, name1=  "new"+1, name2 = "new"+2;
        String saveDir = defaultSavePath+System.getProperty("file.separator");
        File file = new File(saveDir+name+".csv"),
                file1 = new File(saveDir+name1+".csv"),
                file2 = new File(saveDir+name2+".csv");

        assertTrue(file.exists());
        assertTrue(file1.exists());
        assertTrue(file2.exists());
    }

    @Test
    void shouldSaveThreeFilesIntoTheCustomFolder() {
        defaultAddressBooks();
        String customDir = System.getProperty("user.home") + System.getProperty("file.separator") + "addresses";
        File customDirFile = new File(customDir);
        if(!customDirFile.exists()){
            boolean dirCreated = customDirFile.mkdir();
            System.out.println("Custom directory has been created, " + dirCreated);
        }
        this.saver = new AddressBookSaver(addressBooks, customDir+System.getProperty("file.separator"));
        saver.save();

        String name = "new"+0, name1=  "new"+1, name2 = "new"+2;
        File file = new File(customDir+System.getProperty("file.separator")+name+".csv"),
                file1 = new File(customDir+System.getProperty("file.separator")+name1+".csv"),
                file2 = new File(customDir+System.getProperty("file.separator")+name2+".csv");

        assertTrue(file.exists());
        assertTrue(file1.exists());
        assertTrue(file2.exists());
    }

    @Test
    void savedFilesShouldContainEntryData() {
        // saving books into default path
        defaultAddressBooks();
        this.saver = new AddressBookSaver(addressBooks);
        saver.save();
        // getting the address books files by names
        String bookName = "new"+0, bookName1=  "new"+1, bookName2 = "new"+2;
        String saveDir = defaultSavePath+System.getProperty("file.separator");

        // initializing address book arrayList to save data into
        List<AddressBook> currentAddressBooks = new ArrayList<>();
        currentAddressBooks.add(new AddressBook(bookName));
        currentAddressBooks.add(new AddressBook(bookName1));
        currentAddressBooks.add(new AddressBook(bookName2));

        // fetching data from csv files
        for(int i = 0 ; i < 3; i++) {
            try(Scanner scanner = new Scanner(Paths.get(saveDir+"new"+i+".csv"))) {
                while(scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    Name name = new Name(data[0], data[1]);
                    Address address = new Address(data[2], data[3], data[4], data[5], data[6]);
                    PhoneNumber number = new PhoneNumber(data[7]);
                    Email email = new Email(data[8]);

                    Entry entry = new Entry(name,address,number, email);
                    currentAddressBooks.get(i).addEntry(entry);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            assertEquals(addressBooks.get(0).getEntryByLastName("last(0;1)").toString(), currentAddressBooks.get(0).getEntryByLastName("last(0;1)").toString());
        }
    }
}
