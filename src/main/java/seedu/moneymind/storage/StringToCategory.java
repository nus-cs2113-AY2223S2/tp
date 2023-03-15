package seedu.moneymind.storage;

import seedu.moneymind.event.Event;
import seedu.moneymind.category.Category;

import java.util.ArrayList;

import static seedu.moneymind.string.Strings.STORAGE_CATEGORY_NAME;
import static seedu.moneymind.string.Strings.STORAGE_NEXT_VARIABLE;

/**
 * Converts a String from file.txt to an ArrayList of Category objects.
 */
public class StringToCategory {
    /**
     * Converts a String from file.txt to an ArrayList of Category objects.
     * 
     * @param inputFromFile String from file.txt
     * @return ArrayList of Category objects
     */
    public static ArrayList<Category> stringToCategory(String inputFromFile) {
        // ArrayList of Categories objects, to be returned
        ArrayList<Category> categories = new ArrayList<>();

        // Reads inputFromFile line by line
        String[] lines = inputFromFile.split(System.lineSeparator());
        for (String string : lines) {
            // Creates a new Category object if the line is a new category, ignoring the save file symbol
            if (string.startsWith(STORAGE_CATEGORY_NAME)) {
                categories.add(new Category(string.substring(STORAGE_CATEGORY_NAME.length())));
            } else if (string.startsWith(STORAGE_NEXT_VARIABLE)) {
                // Remove the first next variable symbol
                string = string.substring(STORAGE_NEXT_VARIABLE.length());
                // Splits the line into 3 parts, each part being a variable of an Event object
                String[] eventDetails = string.split(STORAGE_NEXT_VARIABLE);
                // Creates a new Event object
                Event event = new Event(eventDetails[0], Integer.parseInt(eventDetails[1]),
                        Integer.parseInt(eventDetails[2]));
                // Adds an Event object to the last Category object in the ArrayList
                categories.get(categories.size() - 1).addEvent(event);
            }
        }
        return categories;
    }
}
