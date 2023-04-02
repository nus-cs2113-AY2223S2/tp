package seedu.exceptions;

//import static seedu.ui.ErrorMessages.ERROR_INVALID_DATE_MESSAGE;

public class InvalidDateException extends Exception {
    private static final String CUSTOM_ERROR_MESSAGE = "Date time error! Format: YYYY-MM-DD";
    @Override
    public String getMessage() {
        return CUSTOM_ERROR_MESSAGE;
    }
}


