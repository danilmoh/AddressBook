package com.danilmoh.addressbook.GUI.Services;

import com.danilmoh.addressbook.GUI.WarningManager;
import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBook;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBookFetcher;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.DefaultSavePath;
import com.danilmoh.addressbook.services.lastPath.LastPathSaver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddressBookOpener {

    private final Map<String, AddressBook> bookMap;
    private final ComboBox comboBox;
    private final TableView<Entry> table;

    public AddressBookOpener(Map<String, AddressBook> bookMap, ComboBox comboBox, TableView<Entry> table) {
        this.bookMap = bookMap;
        this.comboBox = comboBox;
        this.table = table;
    }

    public void openAddressBooksFolder(String folderPath, WarningManager manager) {
        List<AddressBook> bookList = fetchAddressBooks(folderPath);
        clearItems();
        fillAddressBookMap(bookList);
        fillComboBox(bookList);

        if (!bookList.isEmpty()){
            openAddressBookFromComboBox();
            manager.clearWarning();
        } else {
            manager.setWarning("Please create or open an address book");
        }

    }

    private List<AddressBook> fetchAddressBooks(String folderPath) {
        AddressBookFetcher fetcher = new AddressBookFetcher(folderPath);
        List<AddressBook> bookList = fetcher.fetch();
        if (bookList.isEmpty()) {
            bookList = new AddressBookFetcher().fetch();
            LastPathSaver.save(DefaultSavePath.SAVE_PATH.getPath());
        }else{
            LastPathSaver.save(folderPath);
        }

        return bookList;
    }

    private void clearItems() {
        bookMap.clear();
        comboBox.getItems().clear();
        table.getItems().clear();
    }

    private void fillAddressBookMap(List<AddressBook> bookList) {
        for(AddressBook book : bookList) {
            bookMap.put(book.getName(), book);
        }
    }

    public void openAddressBookFromComboBox() {
        try {
            openAddressBook(comboBox.getValue().toString());
        }catch (NullPointerException ignored){

        }
    }

    private void fillComboBox(List<AddressBook> bookList) {
        final ObservableList<String> bookNames = FXCollections.observableList(
                bookList.stream().map(AddressBook::getName).collect(Collectors.toList())
        );
        comboBox.setItems(bookNames);
        comboBox.getSelectionModel().selectFirst();
    }

    private void openAddressBook(String name) {
        AddressBook currentBook = bookMap.get(name);
        List<Entry> entryList = currentBook.getEntries();

        final ObservableList<Entry> data = FXCollections.observableList(entryList);
        table.setItems(data);
    }

}
