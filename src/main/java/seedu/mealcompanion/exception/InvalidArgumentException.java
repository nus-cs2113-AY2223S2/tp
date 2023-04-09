package seedu.mealcompanion.exception;

/**
 * Represents a helper class which takes a single argument value
 * and the session state, and performs some validated extraction.
 */
public class InvalidArgumentException extends Exception {
    private String description;

    public InvalidArgumentException(String description) {
        this.description = description;
    }

    @Override
    public String getMessage() {
        return this.description;
    }
}
