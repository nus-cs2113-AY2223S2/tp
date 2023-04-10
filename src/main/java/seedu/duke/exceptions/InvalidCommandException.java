package seedu.duke.exceptions;

public class InvalidCommandException extends Exception {
    private String message;

    public InvalidCommandException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
