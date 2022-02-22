package com.danilmoh.addressbook.GUI.AddressBookHistory;

import com.danilmoh.addressbook.dataObjects.addressBookObjects.DefaultSavePath;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.LastSaveFilePath;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class AddressBookHistoryFetcher {
    private final String lastPathFile;

    public AddressBookHistoryFetcher() {
        this.lastPathFile = LastSaveFilePath.LAST_SAVE_PATH.getPath();
    }

    public String fetchLastPath() {
        try(Scanner scanner = new Scanner(Paths.get(this.lastPathFile))) {
            if(scanner.hasNextLine()) {
                return scanner.nextLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return DefaultSavePath.SAVE_PATH.getPath();
    }
}
