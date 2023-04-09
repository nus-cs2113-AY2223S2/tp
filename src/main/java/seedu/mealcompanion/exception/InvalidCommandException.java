package seedu.mealcompanion.exception;

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
