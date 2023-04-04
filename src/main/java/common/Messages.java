package common;

/**
 * A class containing the list of messages that will be shown to the user. "ERROR for error. MESSAGE for message"
 */
public class Messages {
    /**
     * Messages for programs to print.
     */
    public static final String MESSAGE_VALID_COMMAND_LIST = "List of valid commands:\n" +
            "GENERIC COMMANDS:\n%s\n" +
            "MEETING COMMANDS:\n%s\n" +
            "DEADLINE COMMANDS:\n%s\n" +
            "MENU COMMANDS:\n%s\n" +
            "STAFF COMMANDS:\n%s";
    public static final String MESSAGE_GENERIC_COMMANDS = "help - View list of commands\n" +
            "exit - exits the program\n";
    public static final String MESSAGE_MEETING_COMMANDS = "add_meeting n/<name> t/<time>\n" +
            "delete_meeting n/<meeting>\n" +
            "view_meetings\n" +
            "find_meeting <keyword>\n";
    public static final String MESSAGE_DEADLINE_COMMANDS = "add_deadline n/<name> t/<time>\n" +
            "delete_deadline <integer>\n" +
            "view_deadlines\n" +
            "find_deadlines <keyword>\n";
    public static final String MESSAGE_RECIPE_COMMANDS = "add_dish n/<name> pc/<price in cents> " +
            "[<ingredient 1>;<ingredients 2>;<ingredient 3>; ... ]\n" +
            "delete_dish <integer>\n" +
            "view_dish\n" +
            "find_dish <keyword>\n";

    public static final String MESSAGE_STAFF_COMMANDS =
            "add_staff n/<name> w/<working day> d/<date of birth> p/phone\n" +
            "delete_staff <staff index>\n" +
            "view_staff\n" +
            "find_staff <keyword>\n";
    public static final String MESSAGE_COMMAND_EXIT = "Thank you for using DinerDirector!";
    public static final String MESSAGE_DEADLINE_ADDED = "Got it! This deadline has been successfully added.\n";
    public static final String MESSAGE_DEADLINE_EMPTY_LIST = "Your deadline list is empty!";
    public static final String MESSAGE_DEADLINE_EMPTY_SEARCH_LIST = "No matching deadline found!";
    public static final String MESSAGE_DEADLINE_VIEW_LIST = "Here are the deadlines in your list:";
    public static final String MESSAGE_DEADLINE_VIEW_SEARCH_LIST = "Here are the matching deadlines in your list:";
    public static final String MESSAGE_DEADLINE_REMOVED = "Noted. I've removed this deadline:\n";
    public static final String MESSAGE_NUMBER_OF_DEADLINES = "\nNow you have %d deadlines in the deadline list.";
    public static final String MESSAGE_MEETING_ADDED = "Got it! You have successfully added a meeting:";
    public static final String MESSAGE_MEETING_VIEW_LIST = "Meeting list:";
    public static final String MESSAGE_MEETING_FOUND = "Here's the matching meeting list:";
    public static final String MESSAGE_MEETING_NOT_FOUND  ="There's no such meeting in the meeting list!";
    public static final String MESSAGE_FINISHED_LOADING = "Loading done. Starting Dinerdirector...\n";
    public static final String MESSAGE_STAFF_FOUND = "Here's the matching staff:";
    public static final String MESSAGE_STAFF_NOT_FOUND  ="There's no such staff in the staff list!";
    /**
     * Errors for programs to print.
     */
    public static final String ERROR_COMMAND_INVALID = "Please give a valid command! " +
            "Type \"help\" for list of valid commands";
    public static final String ERROR_DEADLINE_INVALID_INDEX = "Invalid deadline index number!\n" +
            "Enter \"view_deadlines\" to check the index.";
    public static final String ERROR_DEADLINE_MISSING_INDEX = "Delete deadline command must be followed by an index!" +
            "Enter \"view_deadlines\" to check the index.";
    public static final String ERROR_DEADLINE_MISSING_KEYWORD = "Find deadline command must be followed by keyword!";
    public static final String ERROR_DEADLINE_MISSING_PARAM = "Missing deadline parameter!";
    public static final String ERROR_DEADLINE_EXCESS_PARAM = "You cannot have multiple name/time for your deadline!";
    public static final String ERROR_DEADLINE_EXCESS_LIST_PARAM =
            "Excess input detected! Please only type \"view_deadlines\".";
    public static final String ERROR_STAFF_ADD_MISSING_PARAM = "Insufficient information when adding a staff";
    public static final String ERROR_STAFF_ADD_EXCESS_PARAM = "Excess information when adding a staff";
    public static final String ERROR_STAFF_DELETE_MISSING_PARAM = "Insufficient information when deleting a staff";
    public static final String ERROR_STAFF_DELETE_EXCESS_PARAM = "Excess information when deleting a staff";
    public static final String ERROR_STAFF_FIND_MISSING_PARAM = "Insufficient information when finding a staff";
    public static final String ERROR_MEETING_MISSING_PARAM = "Missing meeting parameter!";
    public static final String ERROR_MEETING_EXCESS_ADD_PARAM = "You cannot have multiple name/time for your meeting!";
    public static final String ERROR_MEETING_EXCESS_VIEW_PARAM =
            "Excess input detected! Please only type \"view_meetings\".";
    public static final String ERROR_MEETING_MISSING_INDEX = "Delete command must be followed by an index number!";
    public static final String ERROR_MEETING_INVALID_INDEX="Delete command must be followed by a valid index number!";
    public static final String ERROR_DISH_INVALID_INDEX = "Your index does not contain a valid dish!";
    public static final String ERROR_DISH_NOT_A_VALID_INTEGER = "Your index has to be an integer!";
    public static final String ERROR_DISH_BLANK_DISH_NAME_COMMAND = "Name of dish cannot be empty!";
    public static final String ERROR_DISH_NEGATIVE_PRICE_COMMAND = "Price of dish cannot be negative!";
    public static final String ERROR_DISH_MISSING_INGREDIENT = "Ingredient list cannot be empty!";
    public static final String ERROR_DISH_EMPTY_INDEX = "Index cannot be empty!";
    public static final String ERROR_DISH_MISSING_KEYWORD = "Please key in 1 keyword";
    public static final String ERROR_DISH_MULTIPLE_KEYWORDS = "Please key in only 1 keyword.";
    public static final String ERROR_CREATE_DIRECTORY = "Unable to create directory at\n%s";
    public static final String ERROR_STORAGE_FILE_NOT_FOUND = "The following file was not found:\n%s\n" +
            "A new file will be created when a new task is added.\n";
    public static final String ERROR_STORAGE_INVALID_READ_LINE = "There is an error reading this line.\n%s\n" +
            "Skipping line...\n";
    public static final String ERROR_STORAGE_INVALID_WRITE_LINE = "There is an error writing this line.\n%s\n" +
            "Please check your file permissions.";
    public static final String ERROR_STORAGE_DELETE_FAILED = "There is an error deleting trying " +
            "to delete from the file. Please check your file permissions.";

    public static final String ERROR_STAFF_INVALID_INDEX = "Invalid staff index number";
    public static final String ERROR_STAFF_EXCESS_VIEW_PARAM = "Excessive parameter given to view staff command!";
    public static final String ERROR_PRICE_EXCEED_INTEGER_BOUNDS = "The maximum must not be greater than "
            + Integer.MAX_VALUE + " cents";
}
