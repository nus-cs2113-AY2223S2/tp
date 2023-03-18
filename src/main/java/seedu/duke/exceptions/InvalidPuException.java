package seedu.duke.exceptions;

public class InvalidPuException extends Exception {
    private String message;

    public InvalidPuException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
