package seedu.todolist.constants;

import seedu.todolist.storage.Storage;

/**
 * Enum that holds all the error messages to be displayed.
 */
public enum Errors {
    MISSING_ARGUMENT("Missing argument after: "),

    INVALID_COMMAND("You need to provide a valid command."),
    INVALID_FLAGS("Unknown or duplicate flags are not allowed: "),
    INVALID_ID("Invalid task id: "),
    INVALID_DATE("Invalid date-time: "),
    OLD_DATE("The deadline cannot be before the current system time and date."),
    INVALID_EMAIL("Invalid email address: "),
    INVALID_PRIORITY("Invalid priority: "),
    INVALID_DURATION("Invalid repeat duration: "),
    INVALID_EDIT("Task edit commands must have a " + Flags.EDIT.getName() + " or "
            + Flags.EDIT_DELETE.getName() + " flag."),
    INVALID_FIND_TAG("Unable to find desired tag. Please try again."),

    FAILED_SAVE("Error when saving your data, please check that you have write permissions for "
            + Storage.DEFAULT_SAVE_PATH),
    FAILED_LOAD("Error when loading your data, please check that you have read permissions for "
            + Storage.DEFAULT_SAVE_PATH + System.lineSeparator()
            + "and do not modify it yourself. A new task list will be created for you!"),

    FAILED_CONFIG_SAVE("Error when saving your configuration, please check that you have write permissions for "
                        + Storage.DEFAULT_CONFIG_PATH),
    FAILED_CONFIG_LOAD("Error when loading your configuration, please check that you have read permissions for "
                        + Storage.DEFAULT_CONFIG_PATH + System.lineSeparator()
            + "and do not modify it yourself. Default configurations will be used.");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
