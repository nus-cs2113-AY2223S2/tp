package seedu.moneymind.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import seedu.moneymind.category.Category;

import static seedu.moneymind.storage.CategoriesToString.categoriesToString;
import static seedu.moneymind.storage.GenerateCategoryHashMap.generateCategoryHashMap;
import static seedu.moneymind.storage.ReadFromFile.readFromFile;
import static seedu.moneymind.storage.StringToCategories.stringToCategories;

public class Storage {
    
    private File textFile;
    private ArrayList<Category> savedCategories;
    private HashMap<String, Integer> savedCategoryHashMap;
    
    /**
     * Constructor for Storage class.
     * 
     * @param filePath The path of the file to be created.
     */
    public Storage(String filePath) {
        this.textFile = new File(filePath);
        
        try {
            Boolean isFileCreated = textFile.createNewFile();
            System.out.println((!isFileCreated) ? "Loading file..." : "Creating file...");
        } catch (Exception e) {
            System.out.println("Error creating file..." + e.getMessage());
        }
    }
    
    /**
     * Saves the events to the file.
     *
     * @param categories The ArrayList of events to be saved.
     */
    public void save(ArrayList<Category> categories) {
        String textToWrite = categoriesToString(categories);
        
        moneymindWrite(textToWrite);
    }
    
    /**
     * Writes the text to the file.
     *
     * @param textToWrite The text to be written to the file.
     */
    private void moneymindWrite(String textToWrite) {
        try {
            FileWriter moneymindWriter = new FileWriter(textFile);
            moneymindWriter.write(textToWrite);
            moneymindWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    /**
     * Loads the events from the file.
     *
     * @throws Exception If there is an error loading the file.
     */
    public void load() throws Exception {
        String savedDataString = readFromFile(textFile);
        savedCategories = stringToCategories(savedDataString);
        savedCategoryHashMap = generateCategoryHashMap(savedCategories);
    }
    
    /**
     * Returns the ArrayList created from the saved data.
     * 
     * @return The ArrayList created from the saved data.
     */
    public ArrayList<Category> getSavedCategories() {
        return savedCategories;
    }
    
    /**
     * Returns the HashMap created from the saved data.
     * 
     * @return The HashMap created from the saved data.
     */
    public HashMap<String, Integer> getSavedCategoryHashMap() {
        return savedCategoryHashMap;
    }
    
}
