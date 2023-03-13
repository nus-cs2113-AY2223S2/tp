package common;

/**
 * A class containing the list of messages that will be shown to the user. "ERROR for error. MESSAGE for message"
 */
public class Messages {
    /**
     * Messages for programs to print.
     */
    public static final String MESSAGE_VALID_COMMAND_LIST =
            "List of valid commands:\n" +
            "GENERIC COMMANDS:\n%s\n"+
            "MEETING COMMANDS:\n%s\n"+
            "DEADLINE COMMANDS:\n%s\n"+
            "RECIPE COMMANDS:\n%s\n"+
            "WORKER COMMANDS:\n%s";
    public static final String MESSAGE_GENERIC_COMMANDS =
            "help - View list of commands\n" +
            "exit - exits the program\n";
    public static final String MESSAGE_MEETING_COMMANDS =
            "add_meeting n/<name> t/<time>\n" +
            "delete_meeting n/<meeting>\n" +
            "view_meetings\n";
    public static final String MESSAGE_DEADLINE_COMMANDS =
            "add_deadline n/<name> t/<time>\n" +
            "delete_deadline n/<name>\n" +
            "view_deadlines\n";
    public static final String MESSAGE_RECIPE_COMMANDS =
            "add_recipe n/<name>\n" +
            "delete_recipe n/<name>\n" +
            "view_recipe\n";
    public static final String MESSAGE_WORKER_COMMANDS =
            "add_worker n/<name> w/<working day> d/<date of birth> p/<phone>\n" +
            "delete_worker n/<name>\n" +
            "view_workers\n";
    public static final String MESSAGE_COMMAND_EXIT = "Thank you for using DinerDirector!";

    /**
     * Errors for programs to print.
     */
    public static final String ERROR_COMMAND_INVALID = "Please give a valid command! " +
            "Type \"help\" for list of valid commands";

    public static final String MESSAGE_DEADLINE_ADDED = "Got it! This deadline has been successfully added.\n";
    public static final String MESSAGE_EMPTY_LIST = "Your deadline list is empty!";
    public static final String MESSAGE_VIEW_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_DEADLINE_REMOVED = "Noted. I've removed this task:\n";
    public static final String MESSAGE_NUMBER_OF_DEADLINES = "\nNow you have %d tasks in the list.";

    public static final String MESSAGE_INVALID_INDEX = "Invalid task index number!\n" +
            "Enter \"view_deadline\" to check the index.";
    public static final String MESSAGE_MISSING_INDEX = "Delete command must be followed by the index number!" +
            "Enter \"view_deadline\" to check the index.";
    public static final String MESSAGE_MISSING_PARAM = "Missing deadline parameter!";
    public static final String MESSAGE_EXCESS_PARAM = "You cannot have multiple name/time for your deadline!";
    public static final String MESSAGE_EXCESS_LIST_PARAM = "Excess input detected! Please only type \"view_deadline\".";
    public static final String MESSAGE_FILE_CREATED = "Data file has been created. Your list will be saved.";
    public static final String MESSAGE_FILE_LOADED = "Data file already exists. You list will be updated.";
    public static final String MESSAGE_FILE_MISSING = "Data File Missing! Check if you have accidentally deleted it.";

    public static final String MESSAGE_INVALID_INDEX_FOR_DISH_COMMAND = "Your index does not contain a valid dish!";
    public static final String MESSAGE_NOT_A_VALID_INTEGER_COMMAND = "Your index has to be an integer!";
    public static final String MESSAGE_BLANK_DISH_NAME_COMMAND = "Name of dish cannot be empty!";
    public static final String MESSAGE_NEGATIVE_PRICE_COMMAND = "Price of dish cannot be negative!";

}
