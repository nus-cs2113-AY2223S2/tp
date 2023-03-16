package seedu.moneymind.storage;

import seedu.moneymind.event.Event;
import seedu.moneymind.category.Category;

import static seedu.moneymind.string.Strings.STORAGE_NEXT_VARIABLE;
import static seedu.moneymind.string.Strings.NEW_LINE;
import static seedu.moneymind.string.Strings.STORAGE_CATEGORY_NAME;

/**
 * Converts a Category object to a String.
 */
public class CategoryToString {
    /**
     * Converts a Category object to a String.
     *
     * @param category the Category object to be converted
     * @return the String representation of the Category object
     */
    public static String categoryToString(Category category) {
        // String of category, to be returned
        String output = "";

        // Adds the category name to the output
        output += STORAGE_CATEGORY_NAME + category.getName() + NEW_LINE;
        for (Event event : category.events) {
            output += STORAGE_NEXT_VARIABLE + event.getDescription() + STORAGE_NEXT_VARIABLE + event.getBudget()
                    + STORAGE_NEXT_VARIABLE + event.getExpense() + NEW_LINE;
        }
        return output;
    }
}
