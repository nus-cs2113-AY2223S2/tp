package seedu.mealcompanion.exception;

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
