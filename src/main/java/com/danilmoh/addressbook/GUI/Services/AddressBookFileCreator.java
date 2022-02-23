package com.danilmoh.addressbook.GUI.Services;

import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBook;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBookSaver;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class AddressBookFileCreator {

    public File createAddressBook(Stage mainStage) {
        FileChooser csvFileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV address book format","*.csv");
        csvFileChooser.getExtensionFilters().add(extensionFilter);

        return createAddressBookWithFileChooser(csvFileChooser, mainStage);
    }

    private File createAddressBookWithFileChooser(FileChooser csvFileChooser, Stage stage) {
        File file = csvFileChooser.showSaveDialog(stage);
        if (file != null) {
            createFile(file);
        }

        return file;
    }

    private void createFile(File file) {
        String creationPath = file.getParentFile().getAbsolutePath();
        createAddressBookCsv(creationPath, file.getName());
    }

    private void createAddressBookCsv(String savePath, String bookName){
        AddressBook book = new AddressBook(bookName);
        AddressBookSaver bookSaver = new AddressBookSaver(List.of(book), savePath);
        bookSaver.save();
    }

}
