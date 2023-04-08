package seedu.moneymind.storage;

import java.util.ArrayList;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;

import static seedu.moneymind.string.Strings.STORAGE_CATEGORY_NAME;
import static seedu.moneymind.string.Strings.STORAGE_NEXT_VARIABLE;
import static seedu.moneymind.string.Strings.NEW_LINE;
import static seedu.moneymind.string.Strings.checkForStorageDelimiter;

/**
 * Converts the ArrayList of categories to a String.
 */
public class CategoriesToString {

    /**
     * Converts the ArrayList of categories to a String.
     *
     * @param categories The ArrayList of categories to be converted.
     * @return The String representation of the ArrayList of categories.
     */
    public static String categoriesToString(ArrayList<Category> categories) {
        String categoriesAsString = "";
        for (Category category : categories) {
            categoriesAsString += STORAGE_CATEGORY_NAME + 
                    checkForStorageDelimiter(category.getName());
            if (category.getBudget() != 0) {
                categoriesAsString += STORAGE_CATEGORY_NAME + category.getBudget();
            }
            categoriesAsString += NEW_LINE + eventsToString(category.getEvents());
        }
        return categoriesAsString;
    }

    /**
     * Converts the ArrayList of events to a String.
     *
     * @param events The ArrayList of events to be converted.
     * @return The String representation of the ArrayList of events.
     */
    private static String eventsToString(ArrayList<Event> events) {
        String eventsAsString = "";
        for (Event event : events) {
            eventsAsString += STORAGE_NEXT_VARIABLE + checkForStorageDelimiter(event.getDescription()) 
                    + STORAGE_NEXT_VARIABLE + event.getExpense() + STORAGE_NEXT_VARIABLE 
                    + event.getTime() + STORAGE_NEXT_VARIABLE + event.isOneTimeExpense() + NEW_LINE;
        }
        return eventsAsString;
    }

}
