package seedu.duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * Storage that handles the data from the various text file
 */
public class Storage {
    private static final String dirPath = "data";
    public static void checkFileAccess(String filePath) throws IOException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    public static void writeToFile(String textToAdd, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String textToAppend, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}
