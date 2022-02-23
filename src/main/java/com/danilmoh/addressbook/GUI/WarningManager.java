package com.danilmoh.addressbook.GUI;

import javafx.scene.control.Label;

import static com.danilmoh.addressbook.services.NonNullArrayRequirer.requireNonNull;

public class WarningManager {
     private final Label warningLabel;

     public WarningManager(Label warningLabel) {
         requireNonNull(warningLabel);

         this.warningLabel = warningLabel;
     }

     public void setWarning(String text){
         requireNonNull(text);

         this.warningLabel.setText(text);
     }

     public void clearWarning() {
         this.warningLabel.setVisible(false);
     }
}
