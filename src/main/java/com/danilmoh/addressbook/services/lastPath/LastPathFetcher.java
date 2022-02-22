package com.danilmoh.addressbook.services.lastPath;

import com.danilmoh.addressbook.dataObjects.addressBookObjects.DefaultSavePath;
import com.danilmoh.addressbook.dataObjects.addressBookObjects.LastSaveFilePath;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class LastPathFetcher {
    public static String fetch() {
        try(Scanner scanner = new Scanner(Paths.get(LastSaveFilePath.LAST_SAVE_PATH.getPath()))){
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
        }catch (IOException ignored){

        }

        return DefaultSavePath.SAVE_PATH.getPath();
    }
}
