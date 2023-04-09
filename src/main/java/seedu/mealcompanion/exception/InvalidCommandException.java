package seedu.mealcompanion.exception;

/**
 * Class to throw exceptions specific to MealCompanion
 * when a user's input does not represent a valid command
 */
public class InvalidCommandException extends Exception {
    private String description;
    private Exception cause;

    public InvalidCommandException(String description, Exception cause) {
        this.description = description;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return this.description + " " + cause.getMessage() + "!";
    }
}
