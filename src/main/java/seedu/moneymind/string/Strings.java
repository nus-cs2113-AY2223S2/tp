package seedu.moneymind.string;

public class Strings {

    public static final String EVENT_DELETION_MESSAGE = "Event deleted: ";
    public static final String CATEGORY_DELETION_MESSAGE = "Category deleted: ";
    public static final String NON_EXISTENT_EVENT = "Event does not exist";
    public static final String ENTERING_VALID_TIME_FORMAT_MESSAGE = "Please enter a valid time in the format of " +
            "dd/mm/yyyy hh:mm";
    public static final String NEGATIVE_INTEGER_DETECTING_REGEX = "^-\\d+";
    public static final String ZERO_MATCHING_REGEX = "^0";
    public static final String INTEGER_DETECTING_REGEX = "\\d+";
    public static final String LOAD_ERROR_RISK_MESSAGE =
            "Please correct the save file and restart the program, or risk data loss.";
    public static final String ERROR_LOADING_FILE = "Error loading file. ";
    public static final String LOGO = "  __  __                        __  __ _           _ \n" +
            " |  \\/  |                      |  \\/  (_)         | |\n" +
            " | \\  / | ___  _ __   ___ _   _| \\  / |_ _ __   __| |\n" +
            " | |\\/| |/ _ \\| '_ \\ / _ \\ | | | |\\/| | | '_ \\ / _` |\n" +
            " | |  | | (_) | | | |  __/ |_| | |  | | | | | | (_| |\n" +
            " |_|  |_|\\___/|_| |_|\\___|\\__, |_|  |_|_|_| |_|\\__,_|\n" +
            "                           __/ |                     \n" +
            "                          |___/                      \n";
    public static final String GREETING = "Welcome to Moneymind\n" + LOGO + "How may I help you?";
    public static final String OFFER_HELP = "Type 'help' to see the details of all the commands.";
    public static final String OFFER_HELP_FOR_COMMAND =
            "Type 'summary' to see the summary of all the commands you can use.\n" +
            "Type 'help' to see the details of all the commands.";
    public static final String ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String DATA_FILE = "EventList.txt";
    public static final String BUDGET_LIMIT_MESSAGE =
            "The budget limit is 999999999$, please try to give a smaller budget";
    public static final String INDEX_LIMIT_MESSAGE =
            "The limit for index is 999999999, please try to give a smaller index";
    public static final String EXPENSE_LIMIT_MESSAGE =
            "The expense limit is 999999999$, please try to give a smaller expense";
    public static final String NO_CATEGORY_MESSAGE = "Sorry, the category you are looking for does not exist";
    public static final String DOT = ".";
    public static final String NO_CATEGORIES_TO_VIEW = "There are no categories to view";
    public static final String NO_SEARCH_RESULTS = "Sorry, there is matching search results.";
    public static final String NULL_CATEGORY_ASSERTION = "Category name should not be null";
    public static final String NULL_CATEGORY_LIST_ASSERTION = "Category list should not be null";
    public static final String INTRODUCTION_HELP_COMMAND = "Here are the commands you can use:";
    public static final String SUMMARY = "summary";
    public static final String SHOW_SUMMARY = "Here are the commands you can use:\n";
    public static final String SUMMARY_LIST = SHOW_SUMMARY + "1. help\n" +
            "2. summary\n" +
            "3. category\n" +
            "4. event\n" +
            "5. view\n" +
            "6. edit\n" +
            "7. delete\n" +
            "8. search\n" +
            "9. bye\n";
    public static final String HELP_INSTRUCTION = "1. help - show detailed instructions on how to use the app\n"
            + "Format: help\n" + "Example: help\n";
    public static final String SUMMARY_INSTRUCTION = "2. summary - show a summary of the commands that you can use\n"
            + "Format: summary\n" + "Example: summary\n";
    public static final String CATEGORY_INSTRUCTION = "3. category - add a category to your list\n" +
            "Format: category <name> [(optional) b/<budget number>]\n" + "Example: category food b/2000\n";
    public static final String EVENT_INSTRUCTION = "4. event - add an event to a category\n" +
            "Format: event <name> e/<expense number> [(optional) t/<time>]\n" +
            "Example: event lunch e/10 t/01/01/2020 12:00\n" +
            "(time is optional and the format is dd/mm/yyyy hh:mm)\n";
    public static final String VIEW_INSTRUCTION =
            "5. view - view all the events in a category or all the categories\n" +
            "You can view all the events in a category by specifying the category name\n" +
            "Format: view <category name>\n" + "Example: view food\n" +
            "(category name is optional and if you do not enter a category name, all the categories will be shown)\n";
    public static final String EDIT_INSTRUCTION = "6. edit - edit the expense for an event\n" +
            "Format: edit c/<category name> e/<event index>\n" +
            "Example: edit c/food e/1\n";
    public static final String DELETE_INSTRUCTION = "7. delete - delete an event or a category\n" +
            "Format: delete c/<category name> [(optional) e/<event index>]\n" +
            "Example: delete c/food e/1\n" + "Example: delete c/food\n";
    public static final String SEARCH_INSTRUCTION = "8. search - search for matching events and categories\n" +
            "Format: search <keyword>\n" +
            "Example: search bill\n";
    public static final String BYE_INSTRUCTION = "9. bye - exit the app\n" + "Format: bye\n" + "Example: bye\n";
    public static final String SELECTING_CATEGORY_MESSAGE = "Please select the category you want to add the event to:";
    public static final String GO_BACK_MESSAGE = "Please try again or enter back to go back to the main program";
    public static final String EDIT_EXPENSE_LIMIT_MESSAGE = "The expense limit is 999999999$\n" + GO_BACK_MESSAGE;
    public static final String EDIT_BUDGET_LIMIT_MESSAGE = "The budget limit is 999999999$\n" + GO_BACK_MESSAGE;
    public static final String BACK = "back";
    public static final String EVENT_ADDED_MESSAGE = "New event added: ";
    public static final String NULL_EVENT_ASSERTION = "Event name cannot be null";
    public static final String NON_NEGATIVE_EXPENSE_ASSERTION = "Expense cannot be negative";
    public static final String NO_EVENTS_IN_THIS_CATEGORY_MESSAGE = "Oops! You have no events in this category.";
    public static final String SHOW_CATEGORY_MESSAGE = "Here are all the categories in your list:\n";
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
    public static final String EVENT_REGEX = "^(?<name>[^/]+)(?:\\s+e\\/(?<expense>[^/]+))(?:\\s+t\\/(?<time>.+))?$";
    public static final String CATEGORY_REGEX = "^(?<name>[^/]+?)(?:\\s+b\\/(?<budget>[^/]+))?$";
    public static final String EDIT_REGEX = "^c\\/(?=\\S)([^/]*?)(?:\\s+e\\/([^/]+))?\\s*$";
    public static final String DELETE_REGEX = "^c\\/(?=\\S)([^/]*?)(?:\\s+e\\/([^/]+))?\\s*$";
    public static final String EVENT_FORMAT = "Please following the correct format: " +
            "event <name> e/<expense number> [(optional) t/<time>]";
    public static final String CATEGORY_FORMAT = "Please following the correct format: " +
            "category <name> [(optional) b/<budget number>]";
    public static final String EDIT_FORMAT = "Please following the correct format: " +
            "edit c/<category name> e/<event index>";
    public static final String EMPTY_DESCRIPTION_FOR_EVENT = "OOPS!!! The description of an event cannot be empty.";
    public static final String EMPTY_DESCRIPTION_FOR_CATEGORY =
            "OOPS!!! The description of a category cannot be empty.";
    public static final String NULL_INPUT_ASSERTION = "Input cannot be null";
    public static final String STORAGE_NEXT_VARIABLE = "&&next_detail&&";
    public static final String NEW_LINE = System.lineSeparator();
    public static final String STORAGE_CATEGORY_NAME = "&&new_category&&";
    public static final String STORAGE_DEFAULT_STRING = "&&default_value&&";
    public static final String EXISTED_CATEGORY = "Category already exists";
    public static final String EXTRA_SPACE_REGEX_FORMAT = "\\s+";
    public static final String HELP = "help";
    public static final String NO_DESCRIPTION_FOR_BYE = "Bye command should not have any description";
    public static final String NO_DESCRIPTION_FOR_HELP = "Help command should not have any description";
    public static final String NO_DESCRIPTION_FOR_SUMMARY = "Summary command should not have any description";
    public static final String POSITIVE_INTEGER_FOR_EVENT_INDEX = "Please give a positive integer for event index";
    public static final String NON_NEGATIVE_INTEGER_FOR_EXPENSE = "Please give a non-negative integer for expense";
    public static final String EMPTY_DESCRIPTION_FOR_EDIT = "OOPS!!! The description of an edit cannot be empty.";
    public static final String EMPTY_STRING = "";
    public static final String NON_NEGATIVE_INTEGER_FOR_BUDGET = "Please give a non-negative integer for budget";
    public static final String EDIT = "edit";
    public static final String CATEGORY_DOES_NOT_EXIST_MESSAGE = "Category does not exist!";
    public static final String ENTERING_NON_NEGATIVE_NUMBER_MESSAGE =
            "Please enter a non-negative number or enter back to go back to the main program";
    public static final String NO_SEARCH_KEYWORD_MESSAGE = "Please use the format: search <keyword>";

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
