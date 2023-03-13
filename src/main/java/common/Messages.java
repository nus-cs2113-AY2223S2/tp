package common;

/**
 * A class containing the list of messages that will be shown to the user.
 */
public class Messages {
    public static final String MESSAGE_VALID_COMMAND_LIST = "Give Valid command <placeholder>";
    public static final String MESSAGE_COMMAND_EXIT = "Thank you for using DinerDirector!";
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

}
