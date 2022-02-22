package com.danilmoh.addressbook.dataObjects.addressBookObjects;

import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import com.danilmoh.addressbook.dataObjects.entryObjects.Name;
import com.danilmoh.addressbook.dataObjects.entryObjects.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookFetcherTest {

    String defaultSavePath = DefaultSavePath.SAVE_PATH.getPath();
    List<AddressBook> addressBooks = new ArrayList<>();

    AddressBookFetcher fetcher = new AddressBookFetcher();

    @BeforeEach
    void setup() {
        defaultAddressBooks();
    }

    void defaultAddressBooks() {
        addressBooks.clear();
        for(int i = 0; i < 3; i++) {

            AddressBook current = new AddressBook("new"+i);
            for (int j = 0; j < 2; j++) {
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

    void saveAddressBooks(List<AddressBook> addressBooks, String path) {
        for (AddressBook addressBook : addressBooks) {
            try (FileWriter writer = new FileWriter(path+addressBook.getName()+".csv")) {
                List<Entry> entries = addressBook.getEntries();
                for (Entry entry : entries) {
                    String builder = entry.getName().getFirstName() + "," + entry.getName().getLastName() + "," +
                            entry.getAddress().getCountry() + "," + entry.getAddress().getRegion() + "," +
                            entry.getAddress().getCity() + "," + entry.getAddress().getStreet() + "," +
                            entry.getAddress().getHouse() + "," + entry.getPhoneNumberObj().getValue() + "," +
                            entry.getEmailObj().getValue();
                    writer.write(builder + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void saveAddressBooks(List<AddressBook> addressBooks) {
        saveAddressBooks(addressBooks, defaultSavePath);
    }

    @Test
    void shouldFetchDataFromDefaultPath() {
        defaultAddressBooks();
        saveAddressBooks(addressBooks);

        List<AddressBook> addressBooks = fetcher.fetch();

        for(int i = 0; i < addressBooks.size(); i++) {
            List<Entry> currentEntries = addressBooks.get(i).getEntries();
            for(int j = 0; j < currentEntries.size(); j++) {
                // fetcher should use Arrays.sort() for getting files from folder in order to make this test working
                assertEquals(this.addressBooks.get(i).getEntries().get(j).toString(),currentEntries.get(j).toString());
            }
        }
    }
}
