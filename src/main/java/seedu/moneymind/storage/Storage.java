package seedu.moneymind.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.moneymind.Category;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class to save and load data from a file
 */
public class Storage {
    /**
     * Divider used to separate details of a task saved in file
     */
    private static final String SAVEFILESEPARATOR = ", ";
    private static File textFile;
    private static Scanner textFileScanner;
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

        try {
            textFileScanner = new Scanner(textFile);
        } catch (FileNotFoundException e) {
            System.out.println("I cannot seem to access the saved tasks. Did you perhaps lock it away?");
        }
    }

    /**
     * Saves an ArrayList of Events to EventList.txt file
     * 
     * @param list ArrayList of Events
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
     * Returns an ArrayList of Events from EventList.txt file
     * 
     * @return ArrayList of Events
     */
    public ArrayList<Category> loadFromFile() {
        ArrayList<Category> savedList = new ArrayList<>();

        while (textFileScanner.hasNext()) {
            // TODO: Loading of objects from file
            String[] loadTaskInfo = new String[3];
            loadTaskInfo = textFileScanner.nextLine().split(SAVEFILESEPARATOR, 3);

            // create tasks individually
            switch (loadTaskInfo[0]) {
            case "CATEGORY":
                break;
        
            case "EVENT":
                break;
        
            default:
                break;
            }
        }

        return savedList;
    }
}
