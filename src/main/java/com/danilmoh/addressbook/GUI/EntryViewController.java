package com.danilmoh.addressbook.GUI;

import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBook;
import com.danilmoh.addressbook.dataObjects.entryObjects.Address;
import com.danilmoh.addressbook.dataObjects.entryObjects.Email;
import com.danilmoh.addressbook.dataObjects.entryObjects.Name;
import com.danilmoh.addressbook.dataObjects.entryObjects.PhoneNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EntryViewController {

    private final AddressBook addressBook;

    public EntryViewController(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @FXML
    Button addButton;

    @FXML
    TextField firstNameField, lastNameField, phoneField, emailField, countryField, regionField, cityField, streetField, houseField;

    @FXML
    protected void handleAddButtonAction(ActionEvent event) {
        if (allFieldsContainText()){
            this.addressBook.addEntry(generateEntry());
        }

        closeWindow();
    }

    private Entry generateEntry() {
        Name name = new Name(firstNameField.getText() , lastNameField.getText());
        PhoneNumber number = new PhoneNumber(phoneField.getText());
        Email email = new Email(emailField.getText());
        Address address = new Address(countryField.getText(), regionField.getText(), cityField.getText(),
                streetField.getText(), houseField.getText());
        return new Entry(name, address, number,email);
    }

    private boolean allFieldsContainText() {
        return !firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !phoneField.getText().isEmpty() &&
                !emailField.getText().isEmpty() && !countryField.getText().isEmpty() && !regionField.getText().isEmpty() &&
                !cityField.getText().isEmpty() && !streetField.getText().isEmpty() && !houseField.getText().isEmpty();
    }

    private void closeWindow() {
        Stage stage = (Stage)addButton.getScene().getWindow();
        stage.close();
    }
}
