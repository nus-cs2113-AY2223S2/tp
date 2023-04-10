package seedu.moneymind.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static seedu.moneymind.string.Strings.NEW_LINE;

/**
 * Reads from a file.
 */
public class ReadFromFile {
    
    /**
     * Reads from a file.
     * 
     * @param textFile the path of the file to be read
     * @return the saved tasks
     * @throws Exception
     */
    public static String readFromFile(File textFile) throws Exception {
        Scanner in;
        try {
            in = new Scanner(textFile);
        } catch (FileNotFoundException e) {
            throw new Exception("File not found.");
        }
        String fileString = "";
        while (in.hasNextLine()) {
            fileString += in.nextLine() + NEW_LINE;
        }
        in.close();
        return fileString;
    }
    
}
