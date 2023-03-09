package seedu.duke.storage;

import java.io.IOException;
import java.io.File;

/**
 * The <code>Storage</code> class contains various methods which involves
 * handling of the saved file. For instance, file reading, file writing and saving.
 */
public class Storage {

    private static final String SEPARATOR = " I:I ";
    public static final String DIRECTORY_CREATED = "\nDirectory for file saving created.";
    public static final String DIRECTORY_EXISTS = "\nDirectory for file saving already exists.";
    public static final String FILE_CREATED = "Save file created.";
    public static final String FILE_EXISTS = "Save file already exists.";
    public static final String FILE_PARSING_ERROR = "The task does not meet the parsing requirements for unpacking.";
    private static String filePath;

    /**
     * Sets the <code>filePath</code> to a specific value.
     *
     * @param filePath the String containing the location of the file path to be created.
     */
    public static void setFilePath(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Creates a new file and directory to store
     * save file, if it does not already exist.
     *
     * @throws IOException if an I/O error has occurred.
     */
    public static void createSavedFile() throws IOException {
        File f = new File(filePath);
        File directory = new File("data");
        if (directory.mkdir()) {
            System.out.println(DIRECTORY_CREATED);
        } else {
            System.out.println(DIRECTORY_EXISTS);
        }
        if (f.createNewFile()) {
            System.out.println(FILE_CREATED);
        } else {
            System.out.println(FILE_EXISTS);
        }
        System.out.println();
    }

}
