package seedu.duke.exception;

public class InvalidFlagException extends ToDoListException {
    private static final String ERROR_MESSAGE = "You need to provide at most one of each flag.";

    public InvalidFlagException() {
        super(ERROR_MESSAGE);
    }
}
