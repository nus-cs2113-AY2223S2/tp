package seedu.todolist.constants;

import seedu.todolist.storage.Storage;

/**
 * Enum that holds all the error messages to be displayed.
 */
public final class Errors {
    public static final String MISSING_ARGUMENT = "Missing argument after: ";
    public static final String INVALID_COMMAND = "You need to provide a valid command.";
    public static final String INVALID_FLAGS = "Unknown or duplicate flags are not allowed: ";
    public static final String INVALID_ID = "Invalid task id: ";
    public static final String INVALID_DATE = "Invalid deadline: ";
    public static final String OLD_DATE = "Deadline must be after the current date & time: ";
    public static final String INVALID_EMAIL = "Invalid email address: ";
    public static final String INVALID_PRIORITY = "Invalid priority: ";
    public static final String INVALID_DURATION = "Invalid repeat duration: ";
    public static final String INVALID_EDIT = "Task edit commands must have a "
            + Flags.EDIT.getName() + " or " + Flags.EDIT_DELETE.getName() + " flag.";
    public static final String INVALID_FIND_TAG = "Unable to find desired tag. Please try again.";

    public static final String FAILED_CONFIG_SAVE = "Error when saving your configuration, please check that you have" +
            " write permissions for " + Storage.DEFAULT_CONFIG_PATH;
    public static final String FAILED_CONFIG_LOAD = "Error when loading your configuration, please check that you have "
            + "read permissions for " + Storage.DEFAULT_CONFIG_PATH + System.lineSeparator()
            + "and do not modify it yourself. Default configurations will be used.";

    public static final String FAILED_SAVE = "Error when saving your data; "
            + "please check that you have write permissions for " + Storage.DEFAULT_SAVE_PATH;
    public static final String FAILED_LOAD = "Error when loading your data; "
            + "please check that you have read permissions for " + Storage.DEFAULT_SAVE_PATH + System.lineSeparator()
            + "and do not modify it yourself. A new task list will be created for you!";
    public static final String SAVED_FILE_SYNTAX_ERROR = "There was a problem with your saved file. " +
            "Please correct it before restarting the program.";
    public static final String INVALID_FREQUENCY = "The frequency provided must be greater than or equals to 0.";

    private Errors() {
    }
}
