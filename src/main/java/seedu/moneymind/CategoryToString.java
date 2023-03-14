package seedu.moneymind;

/**
 * Converts a Category object to a String.
 */
public class CategoryToString {
    // String symbol separating each variable of an Event object
    protected static final String NEXT_VARIABLE = "&&next_detail&&";
    // Creates a new line
    protected static final String NEW_LINE = System.lineSeparator();
    // Save file symbol for a new category
    protected static final String CATEGORY_NAME = "&&new_category&&";

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
        output += CATEGORY_NAME + category.getName() + NEW_LINE;
        for (Event event : category.events) {
            output += NEXT_VARIABLE + event.getDescription() + NEXT_VARIABLE + event.getBudget()
                    + NEXT_VARIABLE + event.getExpense() + NEW_LINE;
        }
        return output;
    }
}
