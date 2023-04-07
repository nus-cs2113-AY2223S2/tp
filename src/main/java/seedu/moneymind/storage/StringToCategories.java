package seedu.moneymind.storage;

import java.util.ArrayList;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;

import static seedu.moneymind.string.Strings.STORAGE_CATEGORY_NAME;
import static seedu.moneymind.string.Strings.STORAGE_NEXT_VARIABLE;
import static seedu.moneymind.UserDate.updateDate;
import static seedu.moneymind.string.Strings.NEW_LINE;

/**
 * Converts the String representation of the ArrayList of categories to an ArrayList of categories.
 */
public class StringToCategories {

    /**
     * Converts the String representation of the ArrayList of categories to an ArrayList of categories.
     *
     * @param savedCategories The String representation of the ArrayList of categories to be converted.
     * @return The ArrayList of categories.
     * @throws IllegalArgumentException If the String representation of the ArrayList of categories is invalid.
     */
    public static ArrayList<Category> stringToCategories(String savedCategories) throws IllegalArgumentException {
        String[] savedStrings = savedCategories.split(NEW_LINE);
        ArrayList<Category> categories = new ArrayList<>();
        for (String savedLine : savedStrings) {
            if (savedLine.startsWith(STORAGE_CATEGORY_NAME)) {
                categories.add(loadCategory(savedLine));
            } else if (savedLine.startsWith(STORAGE_NEXT_VARIABLE)) {
                categories.get(categories.size() - 1).addEvent(loadEvent(savedLine));
            } else if (!savedLine.equals("")) {
                throw new IllegalArgumentException("Invalid format found in storage file.");
            }
        }
        return categories;
    }

    /**
     * Converts the String representation of an event to an event.
     *
     * @param savedLine The String representation of the event to be converted.
     * @return The event.
     * @throws IllegalArgumentException If the String representation of the event is invalid.
     */
    private static Event loadEvent(String savedLine) throws IllegalArgumentException {
        String[] savedEvent = savedLine.split(STORAGE_NEXT_VARIABLE);
        Boolean isOneTimeExpense = Boolean.parseBoolean(savedEvent[4]);
        Event eventToBeAdded;
        if (isOneTimeExpense) {
            eventToBeAdded = new Event(savedEvent[1], Integer.parseInt(savedEvent[2]));
            eventToBeAdded.setTime(savedEvent[3]);
            return eventToBeAdded;
        } else if (!isOneTimeExpense) {
            return new Event(savedEvent[1], Integer.parseInt(savedEvent[2]), updateDate(savedEvent[3]));
        } else {
            throw new IllegalArgumentException("Invalid event format in storage file.");
        }
    }

    /**
     * Converts the String representation of a category to a category.
     *
     * @param savedLine The String representation of the category to be converted.
     * @return The category.
     * @throws IllegalArgumentException If the String representation of the category is invalid.
     */
    private static Category loadCategory(String savedLine) throws IllegalArgumentException {
        String[] savedCategory = savedLine.split(STORAGE_CATEGORY_NAME);
        if (savedCategory.length == 2) {
            return new Category(savedCategory[1]);
        } else if (savedCategory.length == 3) {
            return new Category(savedCategory[1], Integer.parseInt(savedCategory[2]));
        } else {
            throw new IllegalArgumentException("Invalid category format in storage file.");
        }
    }

}
