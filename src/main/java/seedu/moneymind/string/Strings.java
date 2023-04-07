package seedu.moneymind.string;

public class Strings {

    public static final String BUDGET_LIMIT_MESSAGE = "The budget limit is 999999999$, please give a smaller budget";
    public static final String INDEX_LIMIT_MESSAGE = "The limit for index is 999999999, please try again.";
    public static final String EXPENSE_LIMIT_MESSAGE = "The expense limit is 999999999$, please give a smaller expense";
    public static final String NO_CATEGORY_MESSAGE = "Category does not exist";
    public static final String CAT_NOT_FOUND = "Category is not found!";
    public static final String DOT = ".";
    public static final String NO_CATEGORIES_TO_VIEW = "There are no categories to view";
    public static final String NO_SEARCH_RESULTS = "No matching search results.";
    public static final String COUNT_ASSERTION = "Count should be greater than 1";
    public static final String NULL_CATEGORY_ASSERTION = "Category name should not be null";
    public static final String NULL_CATEGORY_LIST_ASSERTION = "Category list should not be null";

    public static final String INTRODUCTION_HELP_COMMAND = "Here are the commands you can use:";
    public static final String HELP_INSTRUCTION = "1. help - show instructions on how to use the app\n"
            + "Format: help\n" + "Example: help\n";
    public static final String CATEGORY_INSTRUCTION = "2. category - add a category to your list\n" +
            "Format: category <name> [(optional) b/<budget number>]\n" + "Example: category food b/2000\n";
    public static final String EVENT_INSTRUCTION = "3. event - add an event to a category\n" +
            "Format: event <name> e/<expense number> [(optional) t/<time>]" +
            "Example: event lunch e/10 t/August\n";
    public static final String VIEW_INSTRUCTION =
            "4. view - view all the events in a category or all the categories\n" +
            "You can view all the categories by entering view without any category name\n" +
            "Format: view <category name>\n" + "Example: view food\n";
    public static final String EDIT_INSTRUCTION = "5. edit - edit the expense for an event\n" +
            "edit c/<category name> e/<event index>\n" +
            "Example: edit c/food e/1\n";
    public static final String DELETE_INSTRUCTION = "6. delete - delete an event or a category\n" +
            "delete c/<category name> [(optional) e/<event index>]\n" +
            "Example: delete c/food e/1\n" + "Example: delete c/food\n";
    public static final String SEARCH_INSTRUCTION = "7. search - search for matching events and categories\n" +
            "Format: search <keyword>\n" +
            "Example: search bill\n";
    public static final String BYE_INSTRUCTION = "8. bye - exit the app\n" + "Format: bye\n" + "Example: bye\n";
    public static final String SELECTING_CATEGORY_MESSAGE = "Please select the category you want to add the event to:";
    public static final String GO_BACK_MESSAGE = "Please try again or enter back to go back to the main program";
    public static final String BACK = "back";
    public static final String EVENT_ADDED_MESSAGE = "New event added: ";
    public static final String REMINDING_MESSAGE_TO_GIVE_A_NUMBER = "Please enter a number.";
    public static final String CATEGORY_OUT_OF_RANGE = "The category number you entered is out of range";
    public static final String NON_NEGATIVE_POSITION_ASSERTION = "Category position cannot be negative";
    public static final String NULL_EVENT_ASSERTION = "Event name cannot be null";
    public static final String NON_NEGATIVE_BUDGET_ASSERTION = "Budget cannot be negative";
    public static final String NON_NEGATIVE_EXPENSE_ASSERTION = "Expense cannot be negative";
    public static final String NO_EVENTS_IN_THIS_CATEGORY_MESSAGE = "Oops! You have no events in this category.";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String WHITE_SPACE = " ";
    public static final String BYE = "bye";
    public static final String VIEW = "view";
    public static final String DELETE = "delete";
    public static final String EVENT = "event";
    public static final String CATEGORY = "category";
    public static final String SEARCH = "search";
    public static final String INVALID_INPUT = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String DELETE_FORMAT = "Please following the correct format: " +
            "delete c/<category name> [(optional) e/<event index>]";
    public static final String REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY = "Remember do not leave any things " +
            "inside the brackets empty!";
    public static final String EMPTY_DELETION = "OOPS!!! The description of a delete cannot be empty.";
    public static final String SUBTLE_BUG_MESSAGE = "OOPS!!! Something went wrong, please report to the developer.";
    public static final String EVENT_REGEX = "^(?<name>.*?)(?:\\s+e\\/(?<expense>\\S+))(?: +t\\/(?<time>.*))?$";
    public static final String CATEGORY_REGEX = "^(?!b\\/\\d)(?<name>.*?)(?:\\s+b\\/(?<budget>\\S+))?$";
    public static final String EDIT_REGEX = "c\\/(.+) e\\/(.+)";
    public static final String DELETE_REGEX = "^c/(?=\\S)(.*?)(?:\\s+e/(.*))?\\s*$";
    public static final String EVENT_FORMAT = "Please following the correct format: " +
            "event <name> e/<expense number> [(optional) t/<time>]";
    public static final String CATEGORY_FORMAT = "Please following the correct format: " +
            "category <name> [(optional) b/<budget number>]";
    public static final String EDIT_FORMAT = "Please following the correct format: " +
            "edit c/<category name> e/<event index>";
    public static final String EVENT_EMPTY = "OOPS!!! The description of an event cannot be empty.";
    public static final String CATEGORY_EMPTY = "OOPS!!! The description of a category cannot be empty.";
    public static final String NULL_INPUT_ASSERTION = "Input cannot be null";
    public static final String NULL_DESCRIPTION = "Separated keyword and description cannot be null";
    public static final String STORAGE_NEXT_VARIABLE = "&&next_detail&&";
    public static final String NEW_LINE = System.lineSeparator();
    public static final String STORAGE_CATEGORY_NAME = "&&new_category&&";
    public static final String STORAGE_DEFAULT_STRING = "&&default_value&&";
    public static final String EXISTED_CATEGORY = "Category already exists";
    public static final String EXTRA_SPACE_REGEX_FORMAT = "\\s+";
    public static final String HELP = "help";

    public static final String NO_DESCRIPTION_FOR_BYE = "Bye command should not have any description";
    public static final String NO_DESCRIPTION_FOR_HELP = "Help command should not have any description";
    public static final String POSITIVE_INTEGER_FOR_EVENT_INDEX = "Please give a positive integer for event index";
    public static final String POSITIVE_INTEGER_FOR_EXPENSE = "Please give a positive integer for expense";
    public static final String EMPTY_DESCRIPTION_FOR_EDIT = "OOPS!!! The description of an edit cannot be empty.";
    public static final String EMPTY_STRING = "";
    public static final String POSITIVE_INTEGER_FOR_BUDGET = "Please give a positive integer for budget";
    public static final String EDIT = "edit";
    public static final String CATEGORY_DOES_NOT_EXIST_MESSAGE = "Category does not exist!";
    public static final String ENTERING_POSITIVE_NUMBER_MESSAGE =
            "Please enter a positive number or enter back to go back to the main program";

    /**
     * Returns the input string with all the storage delimiter replaced with the default string.
     *
     * @param input The input string.
     * @return The input string with all the storage delimiter replaced with the default string.
     */
    public static String checkForStorageDelimiter(String input) {
        if (input.contains(STORAGE_CATEGORY_NAME) || input.contains(STORAGE_NEXT_VARIABLE)) {
            System.out.println("Storage delimiter detected and replaced with: &&default_value&&");
            return input.replace(STORAGE_CATEGORY_NAME, STORAGE_DEFAULT_STRING)
                    .replace(STORAGE_NEXT_VARIABLE, STORAGE_DEFAULT_STRING);
        } else {
            return input;
        }
    }
}
