package seedu.moneymind.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.moneymind.Category;
import seedu.moneymind.StringToCategory;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class to save and load data from a file
 */
public class Storage {
    private static File textFile;
    private static String filePath = "EventList.txt";

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
        textFile = new File(filePath);
        
        // create file if it does not exist
        try {
            textFile.createNewFile();
        } catch (IOException e) {
            System.out.println("The file already exists.");
        }
    }

    /**
     * Saves an ArrayList of Category to EventList.txt file
     * 
     * @param list ArrayList of Category
     */
    public void saveToFile(ArrayList<Category> list) {
        String writeToFile = FormatToTxt.formatToTxt(list);

        // write task list to text file
        try {
            FileWriter dukeWriter = new FileWriter(textFile);
            dukeWriter.write(writeToFile);
            dukeWriter.close();
        } catch (IOException e) {
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
            System.out.println("I cannot seem to access the saved tasks. Did you perhaps lock it away?");
        }
        // convert fileString to ArrayList of Category
        ArrayList<Category> savedList = StringToCategory.stringToCategory(fileString);
        return savedList;
    }
}
