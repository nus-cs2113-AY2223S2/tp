package seedu.exceptions;

public class AlreadyMarkException extends Exception {
    @Override
    public String getMessage() {
        return "Sorry! This expenditure is already marked!";
    }
}
