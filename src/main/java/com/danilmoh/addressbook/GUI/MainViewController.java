package com.danilmoh.addressbook.GUI;

import com.danilmoh.addressbook.GUI.Services.AddressBookFileCreator;
import com.danilmoh.addressbook.GUI.Services.AddressBookOpener;
import com.danilmoh.addressbook.GUI.Services.EntryAdder;
import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBook;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBookSaver;
import com.danilmoh.addressbook.services.lastPath.LastPathFetcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;

public class MainViewController {

    @FXML
    public TableView<Entry> table;
    public TableColumn<Entry, String> firstNameCol;
    public TableColumn<Entry, String> lastNameCol;
    public TableColumn<Entry, String> phoneNumberCol;
    public TableColumn<Entry, String> emailCol;
    public TableColumn<Entry, String> countryCol;
    public TableColumn<Entry, String> regionCol;
    public TableColumn<Entry, String> cityCol;
    public TableColumn<Entry, String> streetCol;
    public TableColumn<Entry, String> houseCol;

    @FXML
    public  Button openFolderButton, saveButton, addEntryButton, createAddressBookButton;
    public ComboBox comboBox;

    @FXML
    public Label warningLabel;

    private Map<String, AddressBook> bookMap;
    private WarningManager warningManager;

    private AddressBookOpener addressBookOpener;

    @FXML
    void initialize() {
        this.bookMap = new HashMap<>();
        this.warningManager = new WarningManager(warningLabel);
        this.addressBookOpener = new AddressBookOpener(this.bookMap, this.comboBox, this.table);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        applyCellValueFactoriesForTable();
        applyCellFactoriesForTable();
        applySetOnEditCommitForTable();

        addressBookOpener.openAddressBooksFolder(LastPathFetcher.fetch(), warningManager);
    }

    private void applyCellValueFactoriesForTable() {
        // cell value factories
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        houseCol.setCellValueFactory(new PropertyValueFactory<>("house"));

    }

    private void applySetOnEditCommitForTable() {
        firstNameCol.setOnEditCommit(evt->evt.getRowValue().getName().setFirstName(evt.getNewValue()));
        lastNameCol.setOnEditCommit(evt->evt.getRowValue().getName().setLastName(evt.getNewValue()));
        phoneNumberCol.setOnEditCommit(evt->evt.getRowValue().setPhoneNumber(evt.getNewValue()));
        emailCol.setOnEditCommit(evt->evt.getRowValue().setEmail(evt.getNewValue()));
        countryCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setCountry(evt.getNewValue()));
        regionCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setRegion(evt.getNewValue()));
        cityCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setCity(evt.getNewValue()));
        streetCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setStreet(evt.getNewValue()));
        houseCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setHouse(evt.getNewValue()));

    }

    private void applyCellFactoriesForTable() {
        // cell factories for text fields
        firstNameCol.setCellFactory(forTableColumn());
        lastNameCol.setCellFactory(forTableColumn());
        phoneNumberCol.setCellFactory(forTableColumn());
        emailCol.setCellFactory(forTableColumn());
        phoneNumberCol.setCellFactory(forTableColumn());
        countryCol.setCellFactory(forTableColumn());
        regionCol.setCellFactory(forTableColumn());
        cityCol.setCellFactory(forTableColumn());
        streetCol.setCellFactory(forTableColumn());
        houseCol.setCellFactory(forTableColumn());
    }

    @FXML
    protected void handleOpenFolderButtonAction(ActionEvent event) {
        try {
            openFolderWithDirectoryChooser();
        } catch (NullPointerException ignored) {

        }

    }

    private void openFolderWithDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Path addressBookPath = directoryChooser.showDialog(openFolderButton.getScene().getWindow()).toPath();
        saveCurrentBooks();
        addressBookOpener.openAddressBooksFolder(addressBookPath.toString(), warningManager);
    }

    @FXML
    protected void handleSaveButtonAction(ActionEvent event) {
        saveCurrentBooks();
    }

    @FXML
    protected void handleComboBoxAction(ActionEvent event) {
        addressBookOpener.openAddressBookFromComboBox();
    }

    @FXML
    protected void handleCreateAddressBookButtonAction(ActionEvent event) {
        AddressBookFileCreator bookFileCreator = new AddressBookFileCreator();
        File bookFile = bookFileCreator.createAddressBook((Stage)createAddressBookButton.getScene().getWindow());
        if (bookFile!= null) {
            saveCurrentBooks();
            addressBookOpener.openAddressBooksFolder(bookFile.getParentFile().getAbsolutePath(), warningManager);
        }
    }

    @FXML
    protected void handleAddEntryButtonPressedAction(ActionEvent event) {
        try{
            AddressBook currentOpenedBook = bookMap.get(comboBox.getValue().toString());
            EntryAdder adder = new EntryAdder();
            adder.addEntry(currentOpenedBook);
            table.refresh();
        }catch (NullPointerException ignored){

        }
    }

    public void saveCurrentBooks() {
        Comparator<AddressBook> comparator = Comparator.comparing(AddressBook::getName);
        List<AddressBook> books = this.bookMap.values().stream().sorted(comparator).toList();
        AddressBookSaver bookSaver = new AddressBookSaver(books, LastPathFetcher.fetch());
        bookSaver.save();
    }

}