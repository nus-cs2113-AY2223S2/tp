package seedu.exceptions;

public class InvalidDateException extends Exception {
    private static final String CUSTOM_ERROR_MESSAGE = "Date time error! Format: YYYY-MM-DD";
    @Override
    public String getMessage() {
        return CUSTOM_ERROR_MESSAGE;
    }
}


