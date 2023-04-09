package seedu.mealcompanion.exception;

public class CommandRunException extends Exception {
    /**
     * Constructor for CommandRunException that informs user about problems and errors.
     *
     * @param message The error message to be printed to user.
     */
    public CommandRunException(String message) {
        super(message);
    }
}
