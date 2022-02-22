module com.danilmoh.addressbook.GUI {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.danilmoh.addressbook to javafx.fxml;
    exports com.danilmoh.addressbook.GUI;
    opens com.danilmoh.addressbook.GUI to javafx.fxml;
//    opens com.danilmoh.addressbook.dataObjects to javafx.fxml;
    opens com.danilmoh.addressbook.dataObjects to javafx.base;
    exports com.danilmoh.addressbook.dataObjects;
    exports com.danilmoh.addressbook.dataObjects.entryObjects;
    exports com.danilmoh.addressbook.dataObjects.addressBookObjects;
}