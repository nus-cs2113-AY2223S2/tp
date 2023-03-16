package seedu.moneymind.storage;

import java.util.ArrayList;

import seedu.moneymind.category.Category;

/**
 * Converts an ArrayList of Category objects to a String.
 */
public class FormatToTxt {
    /**
     * Converts an ArrayList of Category objects to a String.
     *
     * @param categories the ArrayList of Category objects to be converted
     * @return the String representation of the ArrayList of Category objects
     */
    public static String formatToTxt(ArrayList<Category> categories) {
        String output = "";
        for (Category category : categories) {
            output += CategoryToString.categoryToString(category);
        }
        return output;
    }
}
