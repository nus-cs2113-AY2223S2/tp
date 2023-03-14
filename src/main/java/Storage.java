import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles data storage and loading.
 * Discerning eyes will realise this is, more or less, taken from Zhan Hong's iP.
 */
public final class Storage {

    private static final String DEFAULT_FILE_PATH = "data/moviemate_data.txt";
    private static final String FILE_DELIMITER = "|";
    private String filePath; // To store custom file path and for methods to reference.

    /**
     * Constructor with custom FilePath defined
     * @param filePath Where the saved files are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        openFile();
    }

    /**
     * Constructor without filepath defined.
     * Uses default file path ({@value DEFAULT_FILE_PATH}) and runs constructor as usual.
     */
    public Storage() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Open the saved file.<br>
     * Checks if the directory and text file exist, and writes to the file.
     */
    private void openFile() {
        File file = new File(this.filePath);

        // Create the directories required (if not exist)
        if (!file.getParentFile().exists()) {
            // Make directories, return true if successful.
            if (file.getParentFile().mkdirs()) {
                System.out.println("Successfully created directory in " + file.getAbsolutePath());
            } else {
                System.out.println("Directory exists or unable to create.");
            }
        }

        // Check if file exists.
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred in creating a new save file.");
            }
            System.out.println("New save file created in " + file.getAbsolutePath());
        } else {
            System.out.println("Loaded your saved movies.");
        }
    }

    /**
     * Loads and parses each line of data in the save file.
     * @return ArrayList ({@link java.util.ArrayList}) of String (Movie in parseable string format).
     */
    public ArrayList<String[]> load(String path) {
        if (path.isEmpty()) {
            // Fallback to default.
            path = this.filePath;
        }
        File f = new File(path);
        Scanner s;
        ArrayList<String[]> output = new ArrayList<String[]>();
        try {
            s = new Scanner(f);
            while (s.hasNext()) {
                String[] split = splitSaveFileLine(s.nextLine());
                output.add(split);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found >_<: " + e.getMessage());
        }
        return output;
    }

    private String[] splitSaveFileLine(String line) {
        return line.split("\\|");
        // return line.split("\\s+" + "\\" + FILE_DELIMITER + "\\s+");
    }

    /**
     * Writes input text to file.
     * This will overwrite existing contents (Intended, for updating)
     * @param textToAdd Everything to be written into the file.
     * @throws IOException Unable to write successfully.
     */
    public void writeToFile(String path, String textToAdd) {
        if (path.isEmpty()) {
            // Fallback to default.
            path = this.filePath;
        }
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(textToAdd);
            // Add newline
            writer.write(System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("Something wrong: " + e.getMessage());
        }
    }
}
