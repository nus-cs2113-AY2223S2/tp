package seedu.duke.exception;

public class InvalidCommandException extends ToDoListException {
    private static final String ERROR_MESSAGE = "You need to provide a valid command.";

    public InvalidCommandException() {
        super(ERROR_MESSAGE);
    }
}
