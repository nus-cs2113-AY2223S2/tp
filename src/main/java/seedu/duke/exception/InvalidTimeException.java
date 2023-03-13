package seedu.duke.exception;

public class InvalidTimeException extends ToDoListException {
    private static final String ERROR_MESSAGE = "You need to provide a date in the correct format (dd-mm-yyyy hh:mm).";

    public InvalidTimeException() {
        super(ERROR_MESSAGE);
    }
}
