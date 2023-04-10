package seedu.moviemate.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles data storage and loading.
 */
public final class Storage {

    private static final String DB_PATH = "/movies_db.csv";
    private static final String DEFAULT_FILE_PATH = "data/moviemate_data.txt";
    private static final String FILE_DELIMITER = "|";
    private final String mainFilePath; // This refers to the default storage path for general information.

    /**
     * Constructor with custom FilePath defined.
     * @param filePath Where the general data is stored.
     */
    public Storage(String filePath) {
        this.mainFilePath = filePath;
        openFile(this.mainFilePath);
    }

    /**
     * Constructor without arguments will default to the default file path ({@value DEFAULT_FILE_PATH}).
     * This calls {@link Storage#Storage(String)} with that file path.
     */
    public Storage() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Opens a file at the specified file path.
     * If it does not exist, this will create the required directories and/or file.
     *
     * @param filePath Specified file path.
     */
    private void openFile(String filePath) {
        File file = new File(filePath);

        // Create the directories required (if it does not exist).
        if (!file.getParentFile().exists()) {
            // Make directories, return true if successful.
            if (file.getParentFile().mkdirs()) {
                System.out.println("Successfully created directory in " + file.getAbsolutePath());
            } else {
                // Unable to create directory, which should be an error.
                System.out.println("Unable to create directories at: " + filePath);
            }
        }

        // Create file if it doesn't exist.
        try {
            // Returns true if created, false if already exists.
            if (file.createNewFile()) {
                System.out.println("New save file created in " + file.getAbsolutePath());
            } else {
                System.out.println("Existing file found: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred in creating file: " + filePath);
        }
    }

    /**
     * Loads and parses each line in the text file at path specified.
     * @param filePath Specified file path.
     * @return ArrayList ({@link java.util.ArrayList}) of String.
     *         Each element is a line split by delimiter.
     */
    public ArrayList<String[]> load(String filePath) {
        if (filePath.isEmpty()) {
            // Fallback to default.
            filePath = this.mainFilePath;
        }

        // Ensure file exists.
        openFile(filePath);

        File f = new File(filePath);
        Scanner s;
        ArrayList<String[]> output = new ArrayList<String[]>();
        try {
            s = new Scanner(f);
            while (s.hasNext()) {
                String[] split = splitSaveFileLine(s.nextLine());
                output.add(split);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved file found: " + e.getMessage());
        }
        return output;
    }

    /**
     * Differs from {@link #load(String)} as its return value is a string
     * @return First line in file.
     */
    public String loadName() {
        File f = new File(this.mainFilePath);
        Scanner s;
        try {
            s = new Scanner(f);
            if (s.hasNext()) {
                return s.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops, something wrong with file:" + this.mainFilePath);
        }
        return "";
    }

    // Helper function to split based on delimiter, with built-in escape character.
    private String[] splitSaveFileLine(String line) {
        return line.split("\\"+ FILE_DELIMITER);
    }

    /**
     * Writes input text to file.
     * This will overwrite existing contents.
     * @param textToWrite Everything to be written into the file.
     */
    public void writeToFile(String path, String textToWrite) {
        if (path.isEmpty()) {
            // Fallback to default.
            path = this.mainFilePath;
        }
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(textToWrite);
            // Add newline
            writer.write(System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("Something wrong: " + e.getMessage());
        }
    }

    /**
     * Calls on CSV Reader to load and read the database.
     * It is using InputStream as the database is meant to be packed in JAR in the respective path {@value DB_PATH}.
     * @return Arraylist of String[], each element is a line, and each String is each value after split by comma.
     */
    public ArrayList<String[]> loadDatabase() {
        InputStream is = getClass().getResourceAsStream(DB_PATH);
        return ReadCSVFile.readEntireCSV(is);
    }
}
