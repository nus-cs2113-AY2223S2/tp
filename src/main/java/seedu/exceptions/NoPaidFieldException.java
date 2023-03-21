package seedu.exceptions;

public class NoPaidFieldException extends Exception {
    @Override
    public String getMessage() {
        return "No paid field for this expenditure!";
    }
}
