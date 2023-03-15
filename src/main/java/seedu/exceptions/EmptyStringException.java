package seedu.exceptions;

public class EmptyStringException extends Exception {
    @Override
    public String getMessage() {
        return "One of your inputs are empty!";
    }
}
