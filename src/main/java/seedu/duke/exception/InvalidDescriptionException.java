package seedu.duke.exception;

public class InvalidDescriptionException extends ToDoListException {
    private static final String ERROR_MESSAGE = "You need to provide a description.";

    public InvalidDescriptionException() {
        super(ERROR_MESSAGE);
    }
}
