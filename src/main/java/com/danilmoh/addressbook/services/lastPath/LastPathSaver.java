package com.danilmoh.addressbook.services.lastPath;

import com.danilmoh.addressbook.dataObjects.addressBookObjects.LastSaveFilePath;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LastPathSaver {
    public static void save(String path) {
        File tempFile = new File(path);
        if (tempFile.exists() && tempFile.isDirectory())
        try(FileWriter writer = new FileWriter(LastSaveFilePath.LAST_SAVE_PATH.getPath(), false)) {
            writer.write(path);
        } catch (IOException ignored){

        }
    }
}
