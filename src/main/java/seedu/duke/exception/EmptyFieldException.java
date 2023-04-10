package seedu.duke.exception;

/**
 * Error occurs due to user input having empty fields
 */
public class EmptyFieldException extends Exception {

    public EmptyFieldException(String message) {
        super("Empty " + message + " is not allowed. Please check " + message + " again!");
    }
}
