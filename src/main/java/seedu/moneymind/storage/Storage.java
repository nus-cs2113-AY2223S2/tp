package seedu.moneymind.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;

import seedu.moneymind.Category;
import seedu.moneymind.CategoryList;
import seedu.moneymind.StringToCategory;

import static seedu.moneymind.Strings.STORAGE_CATEGORY_MAP;
import static seedu.moneymind.Strings.NEW_LINE;

/**
 * Storage class to save and load data from a file
 */
public class Storage {
    private static File textFile;
    private static String filePath = "EventList.txt";
    private static Logger logger = Logger.getLogger("Storage");

    /**
     * Constructor for Storage class
     */
    public Storage() {
        setupFile();
    }

    /**
     * Sets up the file to be read and written to
     */
    private static void setupFile() {
        logger.info("Setting up file");
        // create file object
        textFile = new File(filePath);
        
        // create file if it does not exist
        try {
            textFile.createNewFile();
        } catch (IOException e) {
            logger.warning("File already exists");
            System.out.println("The file already exists.");
        }
    }

    /**
     * Saves an ArrayList of Category to EventList.txt file
     * 
     * @param list ArrayList of Category
     */
    public void saveToFile(ArrayList<Category> list) {
        String writeToFile = FormatToTxt.formatToTxt(list) + CategoryMapToString.categoryMapToString();

        // write task list to text file
        try {
            FileWriter dukeWriter = new FileWriter(textFile);
            dukeWriter.write(writeToFile);
            dukeWriter.close();
        } catch (IOException e) {
            logger.warning("File cannot be accessed");
            System.out.println("I cannot seem to access the saved file. Did you perhaps delete it?");
        }
    }

    /**
     * Loads an ArrayList of Category from EventList.txt file
     * 
     * @return ArrayList of Category
     */
    public ArrayList<Category> loadFromFile() {
        Scanner textFileScanner;
        // String to store the text file
        String fileString = "";

        try {
            textFileScanner = new Scanner(textFile);
            // read file line by line and add to fileString
            while (textFileScanner.hasNextLine()) {
                fileString += textFileScanner.nextLine() + System.lineSeparator();
            }
        } catch (FileNotFoundException e) {
            logger.warning("File cannot be accessed");
            System.out.println("I cannot seem to access the saved tasks. Did you perhaps lock it away?");
        }
        // split fileString into 2 parts using STORAGE_CATEGORY_MAP
        String[] splitString = fileString.split(STORAGE_CATEGORY_MAP + NEW_LINE);
        // convert fileString to ArrayList of Category
        ArrayList<Category> savedList = StringToCategory.stringToCategory(splitString[0]);
        if (splitString.length == 2) {
            logger.info("Loading category map");
            LoadToCategoryMap.loadToCategoryMap(splitString[1]);
        }
        return savedList;
    }

    /**
     * Saves the current list of Category to EventList.txt file
     */
    public void save() {
        logger.info("Saving to file");
        saveToFile(CategoryList.categories);
    }

    /**
     * Loads the list of Category from EventList.txt file
     */
    public void load() {
        logger.info("Loading from file");
        CategoryList.categories = loadFromFile();
    }
}
