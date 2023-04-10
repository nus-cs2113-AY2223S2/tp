package seedu.mealcompanion.exception;

/**
 * Class to throw exceptions specific to MealCompanion
 * when unexpected behaviour occurs while running a command
 */
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
