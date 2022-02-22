package com.danilmoh.addressbook.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    MainViewController controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader. load(), 713, 500);
        controller = fxmlLoader.getController();
        stage.setTitle("Address book");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop(){
        controller.saveCurrentBooks();
    }

    public static void main(String[] args) {
        launch();
    }
}