package seedu.duke.exception;

public class InvalidIndexException extends ToDoListException {
    private static final String ERROR_MESSAGE = "You need to provide a valid index number.";

    public InvalidIndexException() {
        super(ERROR_MESSAGE);
    }
}
