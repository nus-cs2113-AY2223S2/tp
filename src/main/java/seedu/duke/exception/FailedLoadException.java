package seedu.duke.exception;

public class FailedLoadException extends ToDoListException {
    private static final String ERROR_MESSAGE = "Error encountered while loading your data. Please check that you " +
            " have provided the filepath to a file previously saved by Duke. If this is your first time using Duke, " +
            "you can ignore this message.";

    public FailedLoadException() {
        super(ERROR_MESSAGE);
    }
}