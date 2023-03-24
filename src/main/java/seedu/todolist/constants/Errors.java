package seedu.todolist.constants;

import seedu.todolist.storage.Storage;

/**
 * Enum that holds all the error messages to be displayed.
 */
public enum Errors {
    INVALID_COMMAND("You need to provide a valid command."),
    INVALID_FLAGS("You need to provide at most one of each flag."),
    INVALID_DESCRIPTION("You need to provide a description."),
    INVALID_INDEX("You need to provide a valid index number."),
    INVALID_TIME("You need to provide a date in a valid format, such as dd-mm-yyyy hh:mm."),
    INVALID_EMAIL("You need to provide an email in a valid format, such as username@email.com."),
    EMPTY_EMAIL("There is no email set for this task."),
    INVALID_DURATION("You need to provide a number for the repeat occurrence. Put 0 if this is a non-repeating task."),

    FAILED_SAVE("Error when saving your data, please check that you have write permissions for "
            + Storage.DEFAULT_SAVE_PATH),
    FAILED_LOAD("Error when loading your data, please check that you have read permissions for "
            + Storage.DEFAULT_SAVE_PATH + System.lineSeparator()
            + "and do not modify it yourself. A new task list will be created for you!");

    public final String MESSAGE;

    Errors(String message) {
        MESSAGE = message;
    }
}
