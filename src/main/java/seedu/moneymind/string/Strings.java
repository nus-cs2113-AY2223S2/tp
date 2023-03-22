package seedu.moneymind.string;

public class Strings {
    public static final String NO_CATEGORY_MESSAGE = "Category does not exist";
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
            "Format: category <category name>\n" + "Example: category food\n";
    public static final String EVENT_INSTRUCTION = "3. event - add an event to a category\n" +
            "Format: event <event name> b/<budget number> e/<expense number>\n" +
            "Example: event lunch b/10 e/5\n";
    public static final String VIEW_INSTRUCTION =
            "4. view - view all the events in a category or all the categories\n" +
            "You can view all the categories by entering view without any category name\n" +
            "Format: view <category name>\n" + "Example: view food\n";
    public static final String DELETE_INSTRUCTION = "5. delete - delete an event or a category\n" +
            "Format: delete c/<category name> e/<event name>\n" +
            "You can delete a category by entering delete c/<category name> without e/<event name>\n" +
            "Example: delete c/food e/lunch\n" + "Example: delete c/food\n";
    public static final String BYE_INSTRUCTION = "6. bye - exit the app\n" + "Format: bye\n" + "Example: bye\n";
    public static final String SELECTING_CATEGORY_MESSAGE = "Please select the category you want to add the event to: ";
    public static final String GO_BACK_MESSAGE = "Please enter back to go back to the main program";
    public static final String BACK = "back";
    public static final String EVENT_ADDED_MESSAGE = "New event added: ";
    public static final String REMINDING_MESSAGE_TO_GIVE_A_NUMBER = "Please enter a number.";
    public static final String CATEGORY_OUT_OF_RANGE = "The category number you entered is out of range";
    public static final String NON_NEGATIVE_POSITION_ASSERTION = "Category position cannot be negative";
    public static final String NULL_EVENT_ASSERTION = "Event name cannot be null";
    public static final String NON_NEGATIVE_BUDGET_ASSERTION = "Budget cannot be negative";
    public static final String NON_NEGATIVE_EXPENSE_ASSERTION = "Expense cannot be negative";
    public static final String NO_EVENTS_IN_THIS_CATEGORY_MESSAGE = "Opps! You have no events in this category.";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String WHITE_SPACE = " ";
    public static final String BYE = "bye";
    public static final String VIEW = "view";
    public static final String DELETE = "delete";
    public static final String EVENT = "event";
    public static final String CATEGORY = "category";
    public static final String SEARCH = "search";
    public static final String INVALID_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String DELETE_FORMAT = "Please following the correct format: " +
            "delete c/<category name> e/<event name>" +
            " or delete c/<category name>";
    public static final String REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY = "Remember do not leave any things " +
            "inside the brackets empty!";
    public static final String EMPTY_DELETION = "☹ OOPS!!! The description of a delete cannot be empty.";
    public static final String SUBTLE_BUG_MESSAGE = "☹ OOPS!!! Something went wrong, please report to the developer.";
    public static final String EVENT_REGEX = "^(?<name>.*?) e\\/(?<expense>.*?)(?: +t\\/(?<time>.*))?$";
    public static final String CATEGORY_REGEX = "^(?<name>.*?)(?:\\s+b\\/(?<budget>\\S+))?$";
    public static final String EDIT_REGEX = "c\\/(.+) e\\/(.+)";
    public static final String DELETE_REGEX = "^c/(?=\\S)(.*?)(?:\\s+e/(.*))?\\s*$";
    public static final String EVENT_FORMAT = "Please following the correct format: " +
            "event <event name> b/<budget number> e/<expense number>";
    public static final String CATEGORY_FORMAT = "Please following the correct format: " +
            "category <category name> b/<budget number> or category <category name>";
    public static final String EVENT_EMPTY = "☹ OOPS!!! The description of an event cannot be empty.";
    public static final String CATEGORY_EMPTY = "☹ OOPS!!! The description of a category cannot be empty.";
    public static final String NULL_INPUT_ASSERTION = "Input cannot be null";
    public static final String NULL_DESCRIPTION = "Separated keyword and description cannot be null";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_POSITIVE_NUMBER =
            "Please enter a positive number for budget and expense";
    public static final String STORAGE_NEXT_VARIABLE = "&&next_detail&&";
    public static final String NEW_LINE = System.lineSeparator();
    public static final String STORAGE_CATEGORY_NAME = "&&new_category&&";
    public static final String STORAGE_CATEGORY_MAP = "&&category_map&&";
    public static final String EXISTED_CATEGORY = "Category already exists";
    public static final String EXTRA_SPACE_REGEX_FORMAT = "\\s+";
    public static final String HELP = "help";
}
