package com.danilmoh.addressbook.GUI;

import com.danilmoh.addressbook.dataObjects.Entry;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBook;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBookFetcher;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.AddressBookSaver;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.DefaultSavePath;
import com.danilmoh.addressbook.services.lastPath.LastPathFetcher;
import com.danilmoh.addressbook.services.lastPath.LastPathSaver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.danilmoh.addressbook.services.NonNullArrayRequirer.requireNonNull;
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

    @FXML
    void initialize() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.bookMap = new HashMap<>();

        applyCellValueFactoriesForTable();
        applyCellFactoriesForTable();

        openAddressBooksFolder(LastPathFetcher.fetch());
    }

    private void applyCellValueFactoriesForTable() {
        // cell value factories
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setOnEditCommit(evt->evt.getRowValue().getName().setFirstName(evt.getNewValue()));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setOnEditCommit(evt->evt.getRowValue().getName().setLastName(evt.getNewValue()));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumberCol.setOnEditCommit(evt->evt.getRowValue().setPhoneNumber(evt.getNewValue()));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setOnEditCommit(evt->evt.getRowValue().setEmail(evt.getNewValue()));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setCountry(evt.getNewValue()));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        regionCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setRegion(evt.getNewValue()));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        cityCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setCity(evt.getNewValue()));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        streetCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setStreet(evt.getNewValue()));
        houseCol.setCellValueFactory(new PropertyValueFactory<>("house"));
        houseCol.setOnEditCommit(evt->evt.getRowValue().getAddress().setHouse(evt.getNewValue()));

    }

    private void applyCellFactoriesForTable() {
        // cell factories for text fields
        firstNameCol.setCellFactory(forTableColumn());
        lastNameCol.setCellFactory(forTableColumn());
        phoneNumberCol.setCellFactory(forTableColumn());
        emailCol.setCellFactory(
                forTableColumn(
                        new StringConverter<>() {
                            @Override
                            public String toString(String object) {
                                return object;
                            }

                            @Override
                            public String fromString(String string) {
                                return string;
                            }
                        }
                )
        );
        phoneNumberCol.setCellFactory(forTableColumn());
        countryCol.setCellFactory(forTableColumn());
        regionCol.setCellFactory(forTableColumn());
        cityCol.setCellFactory(forTableColumn());
        streetCol.setCellFactory(forTableColumn());
        houseCol.setCellFactory(forTableColumn());
    }

    @FXML
    protected void handleOpenFolderButtonAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        try {
            Path addressBookPath = directoryChooser.showDialog(openFolderButton.getScene().getWindow()).toPath();
            saveCurrentBooks();
            openAddressBooksFolder(addressBookPath.toString());
        } catch (NullPointerException ignored) {

        }

    }

    @FXML
    protected void handleSaveButtonAction(ActionEvent event) {
        saveCurrentBooks();
    }

    @FXML
    protected void handleComboBoxAction(ActionEvent event) {
        try {
            openAddressBook(comboBox.getValue().toString());

        }catch (NullPointerException ignored) {

        }
    }

    @FXML
    protected void handleCreateAddressBookButtonAction(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV address book format","*.csv");
        chooser.getExtensionFilters().add(extensionFilter);

        File file = chooser.showSaveDialog(createAddressBookButton.getScene().getWindow());
        if (file != null) {
            String creationPath = file.getParentFile().getAbsolutePath();
            createAddressBookCsv(creationPath, file.getName());
            saveCurrentBooks();
            openAddressBooksFolder(creationPath);
        }
    }

    @FXML
    protected void handleAddEntryButtonPressedAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("entry-view.fxml"));

        try{
            AddressBook currentOpenedBook = bookMap.get(comboBox.getValue().toString());
            EntryViewController entryController = new EntryViewController(currentOpenedBook);
            loader.setController(entryController);

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();
            table.refresh();
        }catch (NullPointerException ignored){

        }
    }


    public void sayhello() {
        System.out.println("Hello");
    }


    private void openAddressBooksFolder(String folderPath) {
        AddressBookFetcher fetcher = new AddressBookFetcher(folderPath);

        List<AddressBook> bookList = fetcher.fetch();
        if (bookList.isEmpty()) {
            bookList = new AddressBookFetcher().fetch();
            LastPathSaver.save(DefaultSavePath.SAVE_PATH.getPath());
        }else{
            LastPathSaver.save(folderPath);
        }
        clearItems();
        for(AddressBook book : bookList) {
            this.bookMap.put(book.getName(), book);
        }

        final ObservableList<String> bookNames = FXCollections.observableList(
                bookList.stream().map(AddressBook::getName).collect(Collectors.toList())
        );
        comboBox.setItems(bookNames);
        comboBox.getSelectionModel().selectFirst();
        if (!bookList.isEmpty()){
            openAddressBook(comboBox.getValue().toString());
            clearWarning();
        }
        else
            setWarning("Please create or open an address book");
    }

    private void clearItems() {
        this.bookMap.clear();
        this.comboBox.getItems().clear();
        table.getItems().clear();
    }

    private void openAddressBook(String name) {
        AddressBook currentBook = bookMap.get(name);
        List<Entry> entryList = currentBook.getEntries();

        final ObservableList<Entry> data = FXCollections.observableList(entryList);
        table.setItems(data);
    }

    public void saveCurrentBooks() {
        Comparator<AddressBook> comparator = Comparator.comparing(AddressBook::getName);
        List<AddressBook> books = this.bookMap.values().stream().sorted(comparator).toList();
        AddressBookSaver bookSaver = new AddressBookSaver(books, LastPathFetcher.fetch());
        bookSaver.save();
    }

    private void createAddressBookCsv(String savePath, String bookName) {
        File tempFile = new File(savePath);
        if (!tempFile.exists() && !tempFile.isDirectory())
            return;

        AddressBook book = new AddressBook(bookName);
        AddressBookSaver bookSaver = new AddressBookSaver(List.of(book), savePath);
        bookSaver.save();
    }

    private void setWarning(String text) {
        warningLabel.setVisible(true);
        requireNonNull(text);

        warningLabel.setText(text);
    }

    private void clearWarning() {
        warningLabel.setVisible(false);
    }


}