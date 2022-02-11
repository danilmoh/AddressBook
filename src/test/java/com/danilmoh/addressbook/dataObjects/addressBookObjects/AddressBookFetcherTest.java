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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookFetcherTest {

    String defaultSavePath = System.getProperty("user.dir");
    List<AddressBook> addressBooks;

    AddressBookFethcer fetcher = new AddressBookFetcher();

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
                        new PhoneNumber("+1234567"+idStr),
                        new Email("example"+i+j+"@gmail.com")
                ));
            }
            addressBooks.add(current);
        }
    }

    void saveAddressBooks(List<AddressBook> addressBooks, String path) {
        for (AddressBook addressBook : addressBooks) {
            try (FileWriter writer = new FileWriter(path)) {
                List<Entry> entries = addressBook.getEntries();
                for (Entry entry : entries) {
                    StringBuilder builder = new StringBuilder();
                    Entry current = entry;
                    builder.append(current.getName().getFirstName()).append(",").append(current.getName().getLastName()).append(",")
                            .append(current.getAddress().getCountry()).append(",").append(current.getAddress().getRegion()).append(",")
                            .append(current.getAddress().getCity()).append(",").append(current.getAddress().getStreet()).append(",")
                            .append(current.getAddress().getHouse()).append(",").append(current.getPhoneNumber().getValue()).append(",")
                            .append(current.getEmail().getValue());
                    writer.write(builder.toString() + "\n");
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
                assertEquals(currentEntries.get(j), this.addressBooks.get(i).getEntries().get(j));
            }
        }
    }
}