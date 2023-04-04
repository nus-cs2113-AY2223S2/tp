package seedu.pettracker.exceptions;

public class NonPositiveIntegerException extends Exception {
    public NonPositiveIntegerException() {
        super("ERROR: Integer provided should be above 0");
    }
}
