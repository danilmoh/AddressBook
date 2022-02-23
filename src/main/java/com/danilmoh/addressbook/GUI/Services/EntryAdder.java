package com.danilmoh.addressbook.GUI.Services;

import com.danilmoh.addressbook.GUI.Application;
import com.danilmoh.addressbook.GUI.EntryViewController;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBook;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EntryAdder {

    public void addEntry(AddressBook book) {
        addEntryWithEntryViewController(book);
    }

    private void addEntryWithEntryViewController(AddressBook addressbook) {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("entry-view.fxml"));
        EntryViewController entryController = new EntryViewController(addressbook);
        loader.setController(entryController);

        try {
            setUpAddEntryStage(loader);
        }catch (IOException ignored) {

        }
    }

    private void setUpAddEntryStage(FXMLLoader loader) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.showAndWait();
    }
}
