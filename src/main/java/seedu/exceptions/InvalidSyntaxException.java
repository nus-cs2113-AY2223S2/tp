package seedu.exceptions;

public class InvalidSyntaxException extends Exception {

    String error;
    public InvalidSyntaxException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
