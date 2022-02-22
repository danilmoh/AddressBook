package com.danilmoh.addressbook.GUI.AddressBookHistory;

import com.danilmoh.addressbook.dataObjects.addressBookObjects.LastSaveFilePath;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static com.danilmoh.addressbook.services.NonNullArrayRequirer.requireNonNull;

// saves last used folder with address books
public class AddressBookHistorySaver {
    private final String lastPathFile = LastSaveFilePath.LAST_SAVE_PATH.getPath();

    public void saveLastPath(Path folderToRemember){
        requireNonNull(folderToRemember);
        File temp = folderToRemember.toFile();
        if(temp.exists() && temp.isDirectory())
        try (FileWriter writer = new FileWriter(lastPathFile, false)){
            writer.write(folderToRemember.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
