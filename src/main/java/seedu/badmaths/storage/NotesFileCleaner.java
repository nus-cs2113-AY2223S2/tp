package seedu.badmaths.storage;

import java.io.FileWriter;
import java.io.IOException;

public class NotesFileCleaner {
    public static void clearFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write("");
            fileWriter.close();
            System.out.println("File content cleared successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file content.");
            e.printStackTrace();
        }
    }
}


