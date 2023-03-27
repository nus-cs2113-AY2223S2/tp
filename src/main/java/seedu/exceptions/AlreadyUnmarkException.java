package seedu.exceptions;

public class AlreadyUnmarkException extends Exception {
    @Override
    public String getMessage() {
        return "Sorry! This expenditure is already unmarked!";
    }
}
