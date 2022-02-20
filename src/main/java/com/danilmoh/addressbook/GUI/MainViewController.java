package com.danilmoh.addressbook.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class MainViewController {

    @FXML
    public TableView table;

    @FXML
    void initialize() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}